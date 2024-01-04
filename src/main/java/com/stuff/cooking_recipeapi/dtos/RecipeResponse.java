package com.stuff.cooking_recipeapi.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RecipeResponse {
    private Integer id;
    private String name;
    private String description;
    private String imgUrl;
}
