package com.shorewise.wiseconnect.router.routing;


import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.shorewise.wiseconnect.router.model.TFCOLNEW;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class XmlToActiveMQRoute extends RouteBuilder {
   
    private static final Logger logger = LogManager.getLogger(XmlToActiveMQRoute.class);

    @Override
    public void configure() {
        // Configure REST DSL and Swagger
        restConfiguration()
            .component("servlet")
            .port(8092)
            .host("localhost")
            .bindingMode(RestBindingMode.xml)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "shorewise wiseconnect router")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true"); // Enable CORS if needed

        // Define REST service
        rest("/wiseconnect")
            .post("/transactions/submitTomq")
            .consumes(MediaType.APPLICATION_XML_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .type(TFCOLNEW.class)
            .route()
                .process(exchange -> logger.info("Received XML message: {}", exchange.getIn().getBody(String.class)))
                .setExchangePattern(ExchangePattern.InOnly)
                .to("activemq:queue:Ingress")
                .process(exchange -> logger.info("Message forwarded to ActiveMQ queue: Ingress"))
            .endRest();
    }
}
