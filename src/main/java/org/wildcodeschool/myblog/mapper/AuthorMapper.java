package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.AuthorCreateDTO;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.dto.AuthorUpdateDTO;
import org.wildcodeschool.myblog.model.Author;

@Component
public class AuthorMapper {

    public AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstname(author.getFirstname());
        authorDTO.setLastname(author.getLastname());
        return authorDTO;
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstname(authorDTO.getFirstname());
        author.setLastname(authorDTO.getLastname());

        return author;
    }
    public Author convertToEntity(AuthorCreateDTO authorCreateDTO) {
        Author author = new Author();
        author.setFirstname(authorCreateDTO.getFirstname());
        author.setLastname(authorCreateDTO.getLastname());

        return author;
    }
    public Author convertToEntity(AuthorUpdateDTO authorUpdateDTO) {
        Author author = new Author();
        author.setFirstname(authorUpdateDTO.getFirstname());
        author.setLastname(authorUpdateDTO.getLastname());

        return author;
    }
}
