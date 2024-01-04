package com.stuff.cooking_recipeapi.controllers.auth;

import com.stuff.cooking_recipeapi.dtos.JwtRequest;
import com.stuff.cooking_recipeapi.dtos.JwtResponse;
import com.stuff.cooking_recipeapi.services.AuthServices;
import com.stuff.cooking_recipeapi.services.UserService;
import com.stuff.cooking_recipeapi.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthServices authServices;

    @PostMapping("/api/v1/app/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest){
        return authServices.createAuthToken(jwtRequest);
    }
}
