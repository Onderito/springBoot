package org.wildcodeschool.myblog.service;


import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.AuthorCreateDTO;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.dto.AuthorUpdateDTO;
import org.wildcodeschool.myblog.mapper.AuthorMapper;
import org.wildcodeschool.myblog.model.Author;
import org.wildcodeschool.myblog.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    public final AuthorRepository authorRepository;
    public final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::convertToDTO).collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        return authorMapper.convertToDTO(author);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        Author savedAuthor = authorMapper.convertToEntity(authorCreateDTO);
        return authorMapper.convertToDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO authorUpdateDTO) {
        Author author = authorRepository.findById(id).orElseThrow((
                () -> new RuntimeException("Auteur introuvable")
                ));

        author.setFirstname(authorUpdateDTO.getFirstname());
        author.setLastname(authorUpdateDTO.getLastname());
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.convertToDTO(savedAuthor);
    }

    public boolean deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return false;
        }
        authorRepository.delete(author);
        return true;
    }
}
