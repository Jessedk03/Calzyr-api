package com.calzyr.controllers;

import com.calzyr.dto.UsersModel;
import com.calzyr.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public Iterable<UsersModel> getAllUsers() {
        return usersRepository.findAllActiveUsers();
    }

    @GetMapping("/{id}")
    public Optional<UsersModel> getUserById(@PathVariable Integer id) {
        return usersRepository.findById(id);
    }

    @PostMapping
    public UsersModel createUser(@RequestBody UsersModel newUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UsersModel user = new UsersModel();

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setCreatedAt(Timestamp.from(Instant.now()));

        return usersRepository.save(user);
    }

    @PutMapping("/{id}/update")
    public UsersModel updateUser(@PathVariable Integer id, @RequestBody UsersModel userDetails) {
        UsersModel user = usersRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        return usersRepository.save(user);
    }

    @DeleteMapping("/{id}/delete")
    public UsersModel deleteUser(@PathVariable Integer id, @RequestBody UsersModel userDelete) {
        UsersModel user = usersRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        user.setDeletedAt(Timestamp.from(Instant.now()));

        return usersRepository.save(user);
    }
}
