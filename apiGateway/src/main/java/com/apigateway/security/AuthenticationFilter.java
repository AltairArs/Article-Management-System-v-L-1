package com.apigateway.security;

import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.function.Function;

public class AuthenticationFilter {
    public static Function<ServerRequest, ServerRequest> authentication() {
        return (request) -> {
          String authHeader = request.headers().firstHeader("Authorization");
          /*if (authHeader == null || !authHeader.startsWith("Bearer ")) {
              return request;
          }*/
          if (request.path().contains("/api/article")){
              RestClient restClient = RestClient.create("http://authentication-service:8080/graphql");
              HttpSyncGraphQlClient graphQlClient = HttpSyncGraphQlClient.create(restClient);
              String query = """
                      {
                        currentUser{
                            email
                        }
                      }
                      """;
              ClientGraphQlResponse response = graphQlClient.mutate().header("Authorization", authHeader).build().document(query).executeSync();
              if (response.isValid()){
                  ClientResponseField field = response.field("currentUser.email");
                  System.out.println(field.getValue().toString());
              }
          }
          return request;
        };
    }
}
