package com.stuff.cooking_recipeapi.controllers.functions;

import com.stuff.cooking_recipeapi.dtos.JwtResponse;
import com.stuff.cooking_recipeapi.dtos.RecipeRequest;
import com.stuff.cooking_recipeapi.dtos.UserDto;
import com.stuff.cooking_recipeapi.models.Recipe;
import com.stuff.cooking_recipeapi.services.RecipeService;
import com.stuff.cooking_recipeapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/api/v1/app/show_recipe")
    public ResponseEntity<?> showAllRecipes(){
        return recipeService.showAllRecipes();
    }

    @GetMapping("/api/v1/app/user/profile_data")
    public UserDto showDataProfile(@RequestParam String token){
        return userService.getDataUser(token);
    }

    @PostMapping("/api/v1/app/create_recipe")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeRequest recipeDto){
        Recipe recipe = recipeService.createNewRecipe(recipeDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
