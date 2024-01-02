package com.shorewise.wiseconnect.router.beanprocessor;

import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component("dataStoreBeanProcessor")
public class DataStoreBeanProcessor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(DataStoreBeanProcessor.class);

    public void process(@Body String body, Exchange exchange) {
        logger.info("Processing message with Body: {}", body);

        String xmlData = extractXmlFrom(body); // Implement this method based on your data format
        String operation = extractOperationFrom(xmlData);
        String status = extractStatusFrom(body); // Similarly extract status
        String user = extractUserFrom(body); // And the user
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String insertSQL = "INSERT INTO xml_storage.TransactionData "
                + "(id, ti_request, operation_type, status, created_user, updated_user, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        try {
            logger.info("Inserting record into database");
            int res = insertRecord(insertSQL, uuidString, xmlData, operation, status, user);
            logger.info("Record inserted successfully with ID: {}", uuidString);
            exchange.getOut().setBody(uuidString);
        } catch (DataAccessException e) {
            logger.error("Record cannot be inserted/updated into DB due to an exception.", e);
            exchange.getOut().setBody("Error inserting record");
        }
    }

    //Insert new entry into xml_storage.transactional_xml
    private int insertRecord(String insertSQL, String uuidString, String xmlData, String operation, String status, String createdUser) {
        return jdbcTemplate.update(insertSQL, uuidString, xmlData, operation, status, createdUser, createdUser);
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

    private String extractOperationFrom(String xmlData) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(xmlData)));
            doc.getDocumentElement().normalize();

            return getElementValue(doc, "urn:control.services.tiplus2.misys.com", "Operation");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("XML cannot be parsed due to an exception.", e);
            return "";
        }
    }

    private String getElementValue(Document doc, String url, String element) {
        NodeList list = doc.getElementsByTagNameNS(url, element);
        return list.getLength() != 0 ? list.item(0).getTextContent() : "";
    }

    
}
