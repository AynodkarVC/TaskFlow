package org.aynodkar.taskflow.controller;

import org.aynodkar.taskflow.entity.User;
import org.aynodkar.taskflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
        Optional<User> oldUser = userService.getUserById(id);
        if (oldUser.isPresent()) {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        Optional<User> oldUser = userService.getUserById(id);
        if (oldUser.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
        Optional<User> oldUser = userService.getUserById(id);
        if (oldUser.isPresent()) {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);

        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
