package com.example.demo.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomGraphQLController {

    @Autowired
    private GraphQL graphQL;

    // Accepts the raw GraphQL query as plain text in the request body
    @PostMapping("/tbd")
    public Map<String, Object> executeGraphQLQuery(@RequestBody String query) {
        // Prepare the ExecutionInput with the provided query
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .build();

        // Execute the query using the GraphQL instance
        ExecutionResult executionResult = graphQL.execute(executionInput);

        // Return the result as a Map for JSON serialization
        return executionResult.toSpecification();
    }
}

