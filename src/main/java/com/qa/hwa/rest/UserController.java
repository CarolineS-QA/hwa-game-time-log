package com.qa.hwa.rest;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.service.readAllUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user){
        return new ResponseEntity<>(this.service.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findUserById(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user){
    return ResponseEntity.ok(this.service.updateUser(id, user));
    }
}
