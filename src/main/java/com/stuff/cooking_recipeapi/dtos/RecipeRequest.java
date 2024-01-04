package com.stuff.cooking_recipeapi.dtos;

import lombok.Data;

@Data
public class RecipeRequest {
    private String name;
    private String description;
    private String imgUrl;
}
