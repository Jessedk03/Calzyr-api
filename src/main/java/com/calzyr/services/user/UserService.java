package com.calzyr.services.user;

import com.calzyr.dtos.company.CompanyDetailsDTO;
import com.calzyr.dtos.company.CompanyResponseDTO;
import com.calzyr.dtos.event.EventResponseDTO;
import com.calzyr.dtos.subscription.SubscriptionResponseDTO;
import com.calzyr.dtos.user.UserDetailsResponseDTO;
import com.calzyr.dtos.user.UserResponseDTO;
import com.calzyr.entities.company.Company;
import com.calzyr.entities.event.Event;
import com.calzyr.entities.subscription.Subscription;
import com.calzyr.entities.user.User;
import com.calzyr.exceptions.NotFoundException;
import com.calzyr.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CompanySubscriptionRepository companySubscriptionRepository;

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
                .orElseThrow(() -> new NotFoundException("User not found"));
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
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setUsername(oldUserData.getUsername());
        user.setEmail(oldUserData.getEmail());
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        User newUserData = userRepository.save(user);
        return new UserResponseDTO(newUserData);
    }

    //    Delete user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setDeletedAt(Timestamp.from(Instant.now()));

        userRepository.save(user);
    }

    public UserDetailsResponseDTO userDetails(Integer userId) {


        // make this  for each loop again
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Integer> companyOfUser = companyUserRepository.findCompanyOfUser(user.getId());
        List<Company> companies = companyRepository.findCompaniesId(companyOfUser);

        List<Integer> companyIds = companies.stream()
                .map(Company::getId)
                .toList();

        List<Integer> companySubscriptionList = companySubscriptionRepository.findSubscriptionOfCompanies(companyIds);

        List<Subscription> subscriptions = subscriptionRepository.findAllSubscriptionsById(companySubscriptionList);

        List<CompanyResponseDTO> companyDto = companies.stream()
                .map(CompanyResponseDTO::new)
                .toList();

        List<SubscriptionResponseDTO> subscriptionDto = subscriptions.stream()
                .map(SubscriptionResponseDTO::new)
                .toList();

        return new UserDetailsResponseDTO(user, List.of(new CompanyDetailsDTO(companyDto, subscriptionDto)));
    }
}
