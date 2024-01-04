package com.stuff.cooking_recipeapi.repositories;

import com.stuff.cooking_recipeapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
}
