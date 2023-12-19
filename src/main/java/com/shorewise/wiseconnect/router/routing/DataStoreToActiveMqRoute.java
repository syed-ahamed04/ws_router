package com.shorewise.wiseconnect.router.routing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.shorewise.wiseconnect.router.model.TransactionalXmlDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DataStoreToActiveMqRoute extends RouteBuilder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void configure() {

        // REST API endpoint configuration
        rest("/wiseconnect")
            .post("/datastoreToActiveMQ")
                .route()
                .to("direct:processData")
                .transform().simple("${header.CamelMessageId}") // Transform the response to the message ID
                .endRest();

        // Route to process data
        from("direct:processData")
            .to("sql:select * from xml_storage.transactional_xml where status = 'Received'?dataSource=dataSource")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    List<Map<String, Object>> rows = exchange.getIn().getBody(List.class);
                    if (rows.isEmpty()) {
                    	exchange.getIn().setBody("No records to process");
                    	 exchange.getIn().removeHeaders("*"); // Remove headers to avoid further processing
                        exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
                        return;
                    }
                    List<TransactionalXmlDto> records = new ArrayList<>();
                    for (Map<String, Object> row : rows) {
                        TransactionalXmlDto record = new TransactionalXmlDto();
                        record.setId((String) row.get("id"));
                        record.setXmlData((String) row.get("xml_data"));
                        record.setStatus((String) row.get("status"));
                        record.setCreatedUser((String) row.get("created_user"));
                        records.add(record);
                    }
                    exchange.getIn().setBody(records);
                }
            })
            .split(body())
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    TransactionalXmlDto record = exchange.getIn().getBody(TransactionalXmlDto.class);
                    if (record != null && record.getXmlData() != null && !record.getXmlData().isEmpty()) {
                        exchange.getIn().setBody(record.getXmlData());
                        jdbcTemplate.update("UPDATE xml_storage.transactional_xml SET status = 'Processed' WHERE id = ?", record.getId());
                    }
                }
            })
            .marshal().jaxb()
            .setExchangePattern(ExchangePattern.InOnly)
            .to("activemq:queue:ProcessedQueue");
    }
}
