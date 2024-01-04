package com.stuff.cooking_recipeapi.services;

import com.stuff.cooking_recipeapi.dtos.JwtResponse;
import com.stuff.cooking_recipeapi.dtos.RegUserDto;
import com.stuff.cooking_recipeapi.dtos.UserDto;
import com.stuff.cooking_recipeapi.models.User;
import com.stuff.cooking_recipeapi.repositories.RoleRepository;
import com.stuff.cooking_recipeapi.repositories.UserRepository;
import com.stuff.cooking_recipeapi.utils.JwtTokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtils jwtTokenUtils;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserDto getDataUser(String token){
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        return new UserDto(user.get().getId(), user.get().getUsername(), user.get().getEmail());
    }

    public User createNewUser(RegUserDto userDto){
        User user = new User();
        if (findByUsername(userDto.getUsername()).isEmpty()){
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(roleRepository.findByName("ROLE_USER").get());
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
}
