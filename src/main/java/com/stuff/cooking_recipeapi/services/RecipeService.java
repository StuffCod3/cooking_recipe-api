package com.stuff.cooking_recipeapi.services;

import com.stuff.cooking_recipeapi.dtos.RecipeRequest;
import com.stuff.cooking_recipeapi.dtos.RecipeResponse;
import com.stuff.cooking_recipeapi.models.Recipe;
import com.stuff.cooking_recipeapi.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Recipe createNewRecipe(RecipeRequest recipeRequest){
        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setImgUrl(recipeRequest.getImgUrl());
        return recipeRepository.save(recipe);
    }

    public ResponseEntity<List<RecipeResponse>> showAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeResponse> recipeResponses = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeResponse recipeResponse = new RecipeResponse();
            recipeResponse.setId(recipe.getId());
            recipeResponse.setName(recipe.getName());
            recipeResponse.setDescription(recipe.getDescription());
            recipeResponse.setImgUrl(recipe.getImgUrl());
            recipeResponses.add(recipeResponse);
        }

        return new ResponseEntity<>(recipeResponses, HttpStatus.OK);
    }

}
