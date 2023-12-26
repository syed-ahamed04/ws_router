package com.shorewise.wiseconnect.router.routing;

import java.util.Map;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

@Component
public class DataStoreToActiveMqRoute extends RouteBuilder {

    @Override
    public void configure() {
    	
    	// Configure REST DSL to use swagger
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "DataStore to ActiveMQ API")
            .apiProperty("api.version", "1.0");

        // REST endpoint to trigger the process
        rest("/wiseconnect")
            .post("/transactions/extract-and-sendTomq")
            .consumes(MediaType.APPLICATION_XML_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .to("direct:readAndSendToActiveMQ");

        // Route to read, process, and send records to ActiveMQ
        from("direct:readAndSendToActiveMQ")
            .routeId("datastore-to-activemq-route")
            .log("Reading 'Received' records from the database")
            .to("sql:select id, ti_request from xml_storage.TransactionData where status = 'Received'")
            .split(body())
                .log("Sending XML record to ActiveMQ")
                .process(exchange -> {
                    // Assuming the body is a Map containing the record data including the id
                    String recordId = exchange.getIn().getBody(Map.class).get("id").toString();
                    exchange.getIn().setHeader("recordId", recordId);
                })
                .setExchangePattern(ExchangePattern.InOnly)
                .to("activemq:queue:Processed")
                .log("Record sent to ActiveMQ");

        from("activemq:queue:Processed")
		        .routeId("activemq-to-rest-route")
		        .log("Received message from ActiveMQ queue")
		        .to("bean:activeMqResponseBeanProcessor") // Bean handles the update
		        .log("Record updated in the database");
    }
}
