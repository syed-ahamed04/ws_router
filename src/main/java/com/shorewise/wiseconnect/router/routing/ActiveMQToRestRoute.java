package com.shorewise.wiseconnect.router.routing;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

@Component
public class ActiveMQToRestRoute extends RouteBuilder {
	@Override
    public void configure() {
        // REST configuration
        restConfiguration()
            .component("servlet")
            .port(8092)
            .host("localhost")
            .bindingMode(RestBindingMode.auto)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Camel REST API")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true");

        // REST endpoint to trigger ActiveMQ consumption
        rest("/wiseconnect/triggerActiveMq")
            .post()
            .route()
            .to("direct:startActiveMqConsumption")
            .log("ActiveMQ consumption route triggered")
            .endRest();

        from("direct:startActiveMqConsumption")
        .setExchangePattern(ExchangePattern.InOnly)
        .pollEnrich("activemq:queue:Ingress", (oldExchange, newExchange) -> {
            // Here you can aggregate or combine the messages as needed
            if (oldExchange == null) {
                return newExchange;
            } else {
                // Combine or aggregate the messages in some way
                String oldBody = oldExchange.getIn().getBody(String.class);
                String newBody = newExchange.getIn().getBody(String.class);
                String combinedMessage = oldBody + "\n" + newBody;
                oldExchange.getIn().setBody(combinedMessage);
                return oldExchange;
            }
        })
        .choice()
        .when(body().isNotNull())
            .log("Received and aggregated messages from Ingress: ${body}")
            .to("activemq:queue:Egress") // Push to another queue
            .log("Forwarded message to Egress")
        .otherwise()
            .log("Aggregated message body is null, skipping forwarding to AnotherQueue")
    .end();
	}
}