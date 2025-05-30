package com.mytasks.api.controllers;

import com.mytasks.api.dtos.UserRecordDto;
import com.mytasks.api.models.UserModel;
import com.mytasks.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserRecordDto userRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRecordDto));
    }
}
