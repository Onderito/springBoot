package org.wildcodeschool.myblog.dto;

import jakarta.validation.constraints.Size;

public class AuthorUpdateDTO {

    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
    private String firstname;

    @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 50 caractères.")
    private String lastname;


    //Getters et Setters
    public @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
            String getFirstname() {
        return firstname;
    }

    public void setFirstname(@Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
                             String firstname) {
        this.firstname = firstname;
    }

    public @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 50 caractères.")
            String getLastname() {
        return lastname;
    }

    public void setLastname(@Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 50 caractères.")
                            String lastname) {
        this.lastname = lastname;
    }
}
