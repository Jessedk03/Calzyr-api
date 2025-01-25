package com.calzyr.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    public static void loadenv(){
        Dotenv dotenv = Dotenv.configure()
                .filename("env")
                .load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}
