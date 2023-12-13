package com.shorewise.wiseconnect.router.routing;

import java.util.List;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.shorewise.wiseconnect.router.model.ServiceRequest;


@Component
public class XmlToActiveMQRoute extends RouteBuilder {
   
	
	 @Override
	    public void configure() {
	        // Configure REST DSL and Swagger
	        restConfiguration()
	            .component("servlet")
	            .port(8092)
	            .host("localhost")
	            .bindingMode(RestBindingMode.xml)
	            .apiContextPath("/api-doc")
	            .apiProperty("api.title", "Camel REST API")
	            .apiProperty("api.version", "1.0")
	            .apiProperty("cors", "true"); // Enable CORS if needed

	        // Define REST service
	        rest("/wiseconnect/xmlToActiveMq")
	            .post()
	            .consumes(MediaType.APPLICATION_XML_VALUE)
	            .produces(MediaType.APPLICATION_JSON_VALUE)
	            .type(ServiceRequest.class)
	            .route()
	                .log("Received XML message: ${body}")
	                .setExchangePattern(ExchangePattern.InOnly)
	                .to("activemq:queue:Ingress")
	            .endRest();
	    }
	}