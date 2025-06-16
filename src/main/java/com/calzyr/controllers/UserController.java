package com.calzyr.controllers;

import com.calzyr.entities.user.User;
import com.calzyr.dtos.user.UserResponseDTO;
import com.calzyr.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<?> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/me")
//    public String getUserInformation(Integer id) {
////        Optional<User> userRepo = userRepository.findById(id);
////        if (userRepo.isEmpty()) {
////            return "Optional.empty()";
////        }
////
//////        TODO: Finish Subscription and finish this function idiot.
////        return "";
//    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/events")
    public Iterable<?> getEventsFromUserByUserId(@PathVariable Integer id) {
        return userService.getEventFromUserByUserId(id);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PatchMapping("/{id}/update")
    public UserResponseDTO updateUser(@PathVariable Integer id, @RequestBody User oldUserData) {
        return userService.updateUser(id, oldUserData);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Successfully deleted a user. #" + id);
    }
}
