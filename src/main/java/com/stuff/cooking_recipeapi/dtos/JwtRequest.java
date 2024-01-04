package com.stuff.cooking_recipeapi.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
