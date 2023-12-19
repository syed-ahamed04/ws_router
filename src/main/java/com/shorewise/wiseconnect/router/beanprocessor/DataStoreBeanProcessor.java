package com.shorewise.wiseconnect.router.beanprocessor;

import java.util.UUID;

import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component("dataStoreBeanProcessor")
public class DataStoreBeanProcessor {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final Logger logger = LogManager.getLogger(DataStoreBeanProcessor.class);


    public void process(@Body String body) {
        // Example of parsing the body to extract XML and other data
        // This parsing depends on the format of 'body'
        String xmlData = extractXmlFrom(body); // Implement this method based on your data format
        String status = extractStatusFrom(body); // Similarly extract status
        String createdUser = extractUserFrom(body); // And the user
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        // SQL statement with placeholders for parameters
        String sql = "INSERT INTO xml_storage.transactional_xml (id, xml_data, status, created_user, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        // Update the database using JdbcTemplate
        jdbcTemplate.update(sql, uuidString, xmlData, status, createdUser);
        logger.info("Record inserted successfully with ID: " + uuidString);
    }

    // Example methods to parse the incoming message
    private String extractXmlFrom(String body) {
        // Implement parsing logic here
        return body; // Return extracted XML
    }

    private String extractStatusFrom(String body) {
        // Implement parsing logic here
        return "Received"; // Return extracted status
    }

    private String extractUserFrom(String body) {
        // Implement parsing logic here
        return "truser01"; // Return extracted user
    }
}