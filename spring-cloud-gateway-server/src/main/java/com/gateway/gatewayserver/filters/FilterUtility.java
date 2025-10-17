package com.gateway.gatewayserver.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.HttpHeaders;
import java.util.List;

// Utility class to get and set correlation id in the request headers
// This class will be used by the RequestTraceFilter and ResponseTraceFilter
// to get and set the correlation id in the request headers
// The correlation id is used to trace the request across multiple microservices
// It is generated in the RequestTraceFilter if it is not present in the request headers
@Component                                              // @Component annotation is used to make this class a Spring bean
public class FilterUtility {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}
