package org.wildcodeschool.myblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDTO {

    @NotBlank(message = "Le nom de la catégorie ne peux pas être vide")
    @Size(min = 2, max = 20, message = "Le nom doit contenir entre 2 et 20 caractères")
    private String name;


    //Getters et Setters
    public @NotBlank(message = "Le nom de la catégorie ne peux pas être vide")
            @Size(min = 2, max = 20, message = "Le nom doit contenir entre 2 et 20 caractères")
            String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom de la catégorie ne peux pas être vide")
                        @Size(min = 2, max = 20, message = "Le nom doit contenir entre 2 et 20 caractères")
                        String name) {
        this.name = name;
    }
}
