package com.apigateway.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;

@Configuration
public class GatewayConfiguration {

    private RouterFunction<ServerResponse> getRoute(String serviceName){
        return GatewayRouterFunctions
                .route(serviceName + "-service")
                .route(
                        RequestPredicates.path("/api/" + serviceName),
                        HandlerFunctions.http("http://" + serviceName + "-service")
                )
                .before(BeforeFilterFunctions.rewritePath("/api/" + serviceName, "/graphql"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> articleRoute() {
        return getRoute("article");
    }

    @Bean
    public RouterFunction<ServerResponse> authenticationRoute() {
        return getRoute("authentication");
    }
}
