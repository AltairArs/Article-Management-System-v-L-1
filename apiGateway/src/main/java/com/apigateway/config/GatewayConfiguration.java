package com.apigateway.config;

import com.apigateway.security.AuthenticationFilter;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfiguration {
/*
    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        p -> p.path("/api/article")
                                .filters(f -> f.rewritePath("/api/article", "/graphql"))
                                .uri("http://article-service:8081")
                )
                .route(
                        p -> p.path("/api/auth")
                                .filters(f -> f.rewritePath("/api/authentication", "/graphql"))
                                .uri("http://authentication-service:8082")
                )
                .build();
    }
 */

    private RouterFunction<ServerResponse> getRouteFull(String serviceName, String port){
        return GatewayRouterFunctions
                .route(serviceName + "-service")
                .route(
                        RequestPredicates.path("/api/" + serviceName),
                        HandlerFunctions.http("http://" + serviceName + "-service:" + port)
                )
                .before(AuthenticationFilter.authentication())
                .before(BeforeFilterFunctions.rewritePath("/api/" + serviceName, "/graphql"))
                .build();
    }

    private RouterFunction<ServerResponse> getRoute(String serviceName){
        return getRouteFull(serviceName, "8080");
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
