package com.calzyr.controllers;

import com.calzyr.dto.event.EventDTO;
import com.calzyr.dto.user.UserDTO;
import com.calzyr.dto.user.UserResponseDTO;
import com.calzyr.repositories.EventRepository;
import com.calzyr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private EventRepository eventRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
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

    @GetMapping("/{id}/events")
    public Iterable<EventDTO> getEventsFromUserByUserId(@PathVariable Integer id) {
        return eventRepository.findByUserId(id);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserDTO newUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            UserDTO user = new UserDTO();

            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setCreatedAt(Timestamp.from(Instant.now()));

            UserDTO savedUser = userRepository.save(user);
            return new UserResponseDTO(savedUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{id}/update")
    public UserResponseDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDetails) {
        try {
            UserDTO user = userRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setUpdatedAt(Timestamp.from(Instant.now()));

            UserDTO savedUser = userRepository.save(user);
            return new UserResponseDTO(savedUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            UserDTO user = userRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

            user.setDeletedAt(Timestamp.from(Instant.now()));

            userRepository.save(user);

            return ResponseEntity.ok("User successfully marked as deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
