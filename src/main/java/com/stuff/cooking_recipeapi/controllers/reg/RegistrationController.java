package com.stuff.cooking_recipeapi.controllers.reg;
import com.stuff.cooking_recipeapi.dtos.RegUserDto;
import com.stuff.cooking_recipeapi.dtos.UserDto;
import com.stuff.cooking_recipeapi.models.User;
import com.stuff.cooking_recipeapi.services.UserService;
import com.stuff.cooking_recipeapi.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @PostMapping("/api/v1/app/reg")
    public ResponseEntity<?> createUser(@RequestBody RegUserDto userDto){
        User user = userService.createNewUser(userDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
