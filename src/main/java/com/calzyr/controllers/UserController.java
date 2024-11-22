package com.calzyr.controllers;

import com.calzyr.dto.user.UserDTO;
import com.calzyr.repositories.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Iterable<UserDTO> getAllUsers() {
        return userRepository.findAllActiveUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO newUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserDTO user = new UserDTO();

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setCreatedAt(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @PutMapping("/{id}/update")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDetails) {
        UserDTO user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}/delete")
    public UserDTO deleteUser(@PathVariable Integer id, @RequestBody UserDTO userDelete) {
        UserDTO user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        user.setDeletedAt(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }
}
