package org.wildcodeschool.myblog.dto;

import jakarta.validation.constraints.Size;

public class CategoryUpdateDTO {

    @Size(min = 2, max = 20, message = "Le nom de la catégorie doit contenir entre 2 et 20 caractères")
    private String name;


    //Getters et Setters
    public @Size(min = 2, max = 20, message = "Le nom de la catégorie doit contenir entre 2 et 20 caractères")
            String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 20, message = "Le nom de la catégorie doit contenir entre 2 et 20 caractères")
                        String name) {
        this.name = name;
    }
}
