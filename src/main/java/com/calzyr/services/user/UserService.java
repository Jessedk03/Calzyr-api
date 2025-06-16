package com.calzyr.services.user;

import com.calzyr.dtos.event.EventResponseDTO;
import com.calzyr.dtos.user.UserResponseDTO;
import com.calzyr.entities.event.Event;
import com.calzyr.entities.user.User;
import com.calzyr.repositories.EventRepository;
import com.calzyr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    Get all users
    public Iterable<UserResponseDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAllActiveUsers();
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(new UserResponseDTO(user));
        }
        return dtoList;
    }

    //    Get user by id
    public UserResponseDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserResponseDTO::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //    Get events from the user
    public Iterable<EventResponseDTO> getEventFromUserByUserId(Integer id) {
        Iterable<Event> events = eventRepository.findByUserId(id);
        List<EventResponseDTO> dtoList = new ArrayList<>();
        for (Event event : events) {
            dtoList.add(new EventResponseDTO(event));
        }

        return dtoList;
    }

    //    Create new user
    public UserResponseDTO createUser(User newUser) {
        User user = new User();

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setCreatedAt(Timestamp.from(Instant.now()));

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser);
    }

    //    Update existing user
    public UserResponseDTO updateUser(Integer id, User oldUserData) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUsername(oldUserData.getUsername());
        user.setEmail(oldUserData.getEmail());
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        User newUserData = userRepository.save(user);
        return new UserResponseDTO(newUserData);
    }

    //    Delete user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setDeletedAt(Timestamp.from(Instant.now()));

        userRepository.save(user);
    }

//    Get personal information
}
