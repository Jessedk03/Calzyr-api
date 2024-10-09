package com.calzyr.Controllers;

import com.calzyr.Dtos.UsersModel;
import com.calzyr.Repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public Iterable<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UsersModel> getUserById(@PathVariable Integer id) {
        return usersRepository.findById(id);
    }
}
