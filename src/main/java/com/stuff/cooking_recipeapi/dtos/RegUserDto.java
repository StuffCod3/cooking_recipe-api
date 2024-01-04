package com.stuff.cooking_recipeapi.dtos;

import lombok.Data;

@Data
public class RegUserDto {
    private String username;
    private String email;
    private String password;
}
