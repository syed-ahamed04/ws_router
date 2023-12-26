package com.shorewise.wiseconnect.router.beanprocessor;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("activeMqResponseBeanProcessor")
public class ActiveMqResponseBeanProcessor {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(ActiveMqResponseBeanProcessor.class);

    public void process(@Body String body, @Header("recordId") String recordId) {
        logger.info("Processing message with Body: " + body + " and RecordID: " + recordId);

     // SQL update statement with an additional set clause for status
        String updateStatement = "update xml_storage.TransactionData set ti_response = ?, status = 'Processed' where id = ?";

        // Executing the update
        int updated = jdbcTemplate.update(updateStatement, body, recordId);
        logger.info("Updated " + updated + " rows in xml_storage.TransactionData");
    }
}
