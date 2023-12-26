/*package com.shorewise.wiseconnect.router.routing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQToRestRouteJson extends RouteBuilder {

    private static final Logger logger = LogManager.getLogger(ActiveMQToRestRouteJson.class);

    @Override
    public void configure() {
        

        from("jms:Processed") // Replace 'yourQueueName' with the actual JMS queue name
        .to("bean:activeMqResponseBeanProcessor") // Send the message to your bean
        .process(exchange -> {
            logger.info("XML message processed by ActiveMqResponseBeanProcessor");
            // Additional processing if needed
        });
    }
}*/