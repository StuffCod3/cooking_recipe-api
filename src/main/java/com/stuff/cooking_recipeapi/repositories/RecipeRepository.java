package com.stuff.cooking_recipeapi.repositories;

import com.stuff.cooking_recipeapi.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByName (String name);
    Optional<Recipe> findById (Integer id);
}
