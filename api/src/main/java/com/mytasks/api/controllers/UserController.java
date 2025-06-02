package com.mytasks.api.controllers;

import com.mytasks.api.dtos.AuthResponseRecordDto;
import com.mytasks.api.dtos.UserAuthRecordDto;
import com.mytasks.api.dtos.UserRecordDto;
import com.mytasks.api.infra.security.TokenService;
import com.mytasks.api.models.UserModel;
import com.mytasks.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserRecordDto userRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRecordDto));
    }

    @GetMapping
    public ResponseEntity<UserDetails> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.loadUserByUsername(user.getUsername()));
    }

    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody @Validated UserAuthRecordDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseRecordDto(token));
    }
}
