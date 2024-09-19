package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.*;
import org.wildcodeschool.myblog.exception.ResourceNotFoundException;
import org.wildcodeschool.myblog.mapper.ArticleMapper;
import org.wildcodeschool.myblog.mapper.ImageMapper;
import org.wildcodeschool.myblog.model.*;
import org.wildcodeschool.myblog.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ImageMapper imageMapper;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final AuthorRepository authorRepository;
    private final ArticleAuthorRepository articleAuthorRepository;

    public ArticleService(
            ArticleRepository articleRepository,
            ArticleMapper articleMapper,
            ImageMapper imageMapper,
            CategoryRepository categoryRepository,
            ImageRepository imageRepository,
            AuthorRepository authorRepository,
            ArticleAuthorRepository articleAuthorRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.imageMapper = imageMapper;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.authorRepository = authorRepository;
        this.articleAuthorRepository = articleAuthorRepository;
    }

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(articleMapper::convertToDTO).collect(Collectors.toList());
    }

    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(()
        ->new ResourceNotFoundException("L'article avec l'id " + id + " n'a pas été trouvé"));
        if (article == null) {
            return null;
        }
        return articleMapper.convertToDTO(article);
    }

    public ArticleDTO createArticle(ArticleCreateDTO articleCreateDTO) {
        Article article = articleMapper.convertToEntity(articleCreateDTO);

        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        // Catégorie
        if (article.getCategory() != null) {
            Category category = categoryRepository.findById(article.getCategory().getId()).orElse(null);
            if (category == null) {
                return null;
            }
            article.setCategory(category);
        }
        // Images
        if (article.getImages() != null && !article.getImages().isEmpty()) {
            List<Image> validImages = new ArrayList<>();
            for (Image image : article.getImages()) {
                if (image.getId() != null) {
                    Image existingImage = imageRepository.findById(image.getId()).orElse(null);
                    if (existingImage != null) {
                        validImages.add(existingImage);
                    } else {
                        return null;
                    }
                } else {
                    Image savedImage = imageRepository.save(image);
                    validImages.add(savedImage);
                }
            }
            article.setImages(validImages);
        }
        Article savedArticle = articleRepository.save(article);
        // Auteurs
        if (article.getArticleAuthors() != null) {
            for (ArticleAuthor articleAuthor : article.getArticleAuthors()) {
                Author author = articleAuthor.getAuthor();
                author = authorRepository.findById(author.getId()).orElse(null);
                if (author == null) {
                    return null;
                }

                articleAuthor.setAuthor(author);
                articleAuthor.setArticle(savedArticle);
                articleAuthor.setContribution(articleAuthor.getContribution());

                articleAuthorRepository.save(articleAuthor);
            }
        }
        return articleMapper.convertToDTO(savedArticle);
    }


    public ArticleDTO updateArticle(Long id, ArticleUpdateDTO articleUpdateDTO) {
        Article article = articleRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("L'article avec l'ID " + id + " n'a pas été trouvé."));

        if (articleUpdateDTO.getTitle() != null) {
            article.setTitle(articleUpdateDTO.getTitle());

        }
        if (articleUpdateDTO.getContent() != null) {
            article.setContent(articleUpdateDTO.getContent());

        }
        article.setUpdatedAt(LocalDateTime.now());

        // Catégorie
        if (articleUpdateDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(articleUpdateDTO.getCategoryId()).orElseThrow(()
                -> new ResourceNotFoundException("La catégorie avec l'ID " + articleUpdateDTO.getCategoryId() + " n'existe pas."));
            article.setCategory(category);
        }
        // Images
        if (articleUpdateDTO.getImages() != null) {
            List<Image> validImages = new ArrayList<>();
            for (ImageDTO imageDTO : articleUpdateDTO.getImages()) {
                if (imageDTO.getId() != null) {
                    Image existingImage = imageRepository.findById(imageDTO.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Image non trouvée avec l'ID " + imageDTO.getId()));
                    validImages.add(existingImage);
                } else {
                    Image savedImage = imageRepository.save(imageMapper.convertToEntity(imageDTO));
                    validImages.add(savedImage);
                }
            }
            article.setImages(validImages);
        }
        // Auteurs
        if (articleUpdateDTO.getAuthors() != null) {
            articleAuthorRepository.deleteAll(article.getArticleAuthors());
            List<ArticleAuthor> updatedArticleAuthors = new ArrayList<>();
            for (AuthorContributionDTO authorDTO : articleUpdateDTO.getAuthors()) {
                Author author = authorRepository.findById(authorDTO.getAuthorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Auteur non trouvé avec l'ID " + authorDTO.getAuthorId()));

                ArticleAuthor newArticleAuthor = new ArticleAuthor();
                newArticleAuthor.setAuthor(author);
                newArticleAuthor.setArticle(article);
                newArticleAuthor.setContribution(authorDTO.getContribution());

                updatedArticleAuthors.add(newArticleAuthor);
            }
            articleAuthorRepository.saveAll(updatedArticleAuthors);
            article.setArticleAuthors(updatedArticleAuthors);
        }
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.convertToDTO(updatedArticle);
    }


    public boolean deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return false;
        }

        articleAuthorRepository.deleteAll(article.getArticleAuthors());
        articleRepository.delete(article);
        return true;
    }
}
