package com.authenticationservice.exceptions.handlers;

import com.authenticationservice.exceptions.AccessDeniedException;
import com.authenticationservice.exceptions.EntityAlreadyExistsException;
import com.authenticationservice.exceptions.EntityNotFoundException;
import com.authenticationservice.exceptions.InvalidDataException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class AuthenticationServiceExceptionResolver extends DataFetcherExceptionResolverAdapter {
    private GraphQLError getGraphQLError(Throwable ex, DataFetchingEnvironment env, ErrorType type) {
        return GraphqlErrorBuilder.newError()
                .errorType(type)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return switch (ex){
            case EntityNotFoundException e -> getGraphQLError(e, env, ErrorType.NOT_FOUND);
            case EntityAlreadyExistsException e -> getGraphQLError(e, env, ErrorType.BAD_REQUEST);
            case BindException e -> getGraphQLError(e, env, ErrorType.BAD_REQUEST);
            case AccessDeniedException e -> getGraphQLError(e, env, ErrorType.FORBIDDEN);
            case InvalidDataException e -> getGraphQLError(e, env, ErrorType.BAD_REQUEST);
            default -> null;
        };
    }
}
