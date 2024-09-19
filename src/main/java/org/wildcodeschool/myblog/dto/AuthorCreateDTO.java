package org.wildcodeschool.myblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthorCreateDTO {

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
    private String firstname;

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 60 caractères.")
    private String lastname;


    //Getters et Setters
    public @NotBlank(message = "Le prénom est obligatoire.")
            @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
            String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotBlank(message = "Le prénom est obligatoire.")
                             @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
                             String firstname) {
        this.firstname = firstname;
    }

    public @NotBlank(message = "Le nom est obligatoire.")
            @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 60 caractères.")
            String getLastname() {
        return lastname;
    }

    public void setLastname(@NotBlank(message = "Le nom est obligatoire.")
                            @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 60 caractères.")
                            String lastname) {
        this.lastname = lastname;
    }
}
