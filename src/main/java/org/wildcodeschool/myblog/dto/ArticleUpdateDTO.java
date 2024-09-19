package org.wildcodeschool.myblog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class ArticleUpdateDTO {


    @Size(min = 2, max = 50, message = "Le titre doit contenir entre 2 et 50 caractères")
    private String title;

    @Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
    private String content;

    @Positive(message = "L'ID de la catégorie doit être un nombre positif")
    private Long categoryId;

    private List<@Valid ImageDTO> images;

    private List<@Valid AuthorContributionDTO> authors;


    //Getters et Setters
    public @Size(min = 2, max = 50, message = "Le titre doit contenir entre 2 et 50 caractères")
            String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 2, max = 50, message = "Le titre doit contenir entre 2 et 50 caractères")
                         String title) {
        this.title = title;
    }

    public List<@Valid AuthorContributionDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<@Valid AuthorContributionDTO> authors) {
        this.authors = authors;
    }

    public List<@Valid ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<@Valid ImageDTO> images) {
        this.images = images;
    }

    public @Positive(message = "L'ID de la catégorie doit être un nombre positif")
        Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@Positive(message = "L'ID de la catégorie doit être un nombre positif")
                              Long categoryId) {
        this.categoryId = categoryId;
    }

    public @Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
        String getContent() {
        return content;
    }

    public void setContent(@Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
                           String content) {
        this.content = content;
    }
}
