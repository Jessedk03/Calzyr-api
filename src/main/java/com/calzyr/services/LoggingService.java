package com.calzyr.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class LoggingService {

    private static final Logger LOGGER = Logger.getLogger(LoggingService.class.getName());

    public void archiveLog(String message) throws IOException {
        folderCheck();
        File logFile = new File("logs/archive_logs.txt");

        try (BufferedWriter log = new BufferedWriter(new FileWriter(logFile, true))) {
            log.write(message);
            log.newLine();
        } catch (IOException e) {
            LOGGER.severe("Error writing to log file: " + e.getMessage());
            throw e;
        }
    }

    private void folderCheck() {
        File directory = new File("logs");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
