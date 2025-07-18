package com.calzyr.config.database;

import com.calzyr.entities.company.Company;
import com.calzyr.entities.company.CompanySubscription;
import com.calzyr.entities.company.CompanyUser;
import com.calzyr.entities.subscription.Subscription;
import com.calzyr.entities.user.User;
import com.calzyr.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

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

    @Autowired
    public DatabaseSeeder(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
//        seedUsersTable();
//        seedCompanyTable();
//        seedCompanyUserTable();
//        seedSubscriptionTable();
//        seedCompanySubscriptionTable();
    }

    private void seedSubscriptionTable() {
        Subscription subscription = new Subscription();

        subscription.setSubscriptionName("BASIC");
        subscription.setSubscriptionDescription("Basic version");
        subscription.setBasePriceMonthly(50f);
        subscription.setBasePriceYearly(500f);
        subscription.setExpiresAt(Timestamp.from(Instant.parse("2026-12-03T10:15:30.00Z")));
        subscription.setCreatedAt(Timestamp.from(Instant.now()));

        subscriptionRepository.save(subscription);
    }

    private void seedCompanySubscriptionTable() {
        CompanySubscription companySubscription= new CompanySubscription();

        companySubscription.setCompanyId(1);
        companySubscription.setSubscriptionId(2);
        companySubscription.setUserAmount(50);
        companySubscription.setPricePerUser(10f);
        companySubscription.setTotalPrice(500f);
        companySubscription.setCreatedAt(Timestamp.from(Instant.now()));

        companySubscriptionRepository.save(companySubscription);
    }

    private void seedCompanyTable() {
        Company company = new Company();

        company.setCompanyName("DeKoe International Holding B.V.");
        company.setCocNumber(null);
        company.setVatNumber(null);
        company.setStreetName("Oudlaan");
        company.setStreetNumber(195);
        company.setStreetNumberSuffix(null);
        company.setOfficeNumber(22);
        company.setOfficeFloor(2);
        company.setCity("Utrecht");
        company.setProvinceState("Utrecht");
        company.setCountry("The Netherlands");

        companyRepository.save(company);
    }

    private void seedCompanyUserTable() {
        CompanyUser companyUser = new CompanyUser();

        companyUser.setUserId(1);
        companyUser.setCompanyId(2);

        companyUserRepository.save(companyUser);
    }

    private void seedUsersTable() {
        User user = new User();

        user.setUsername("Isa");
        user.setEmail("IsaBuchelee@gmail.com");
        user.setPassword(passwordEncoder.encode("Test1234!"));
        user.setCreatedAt(Timestamp.from(Instant.now()));

        userRepository.save(user);
    }

}
