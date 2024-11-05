package com.example.demo.config;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.Work;
import com.example.demo.resolver.AddressResolver;
import com.example.demo.resolver.UserResolver;
import com.example.demo.resolver.WorkResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStreamReader;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStreamReader;

@Configuration
public class GraphQLConfig {

    private final UserResolver userResolver;
    private final AddressResolver addressResolver;
    private final WorkResolver workResolver;

    @Autowired
    public GraphQLConfig(UserResolver userResolver, AddressResolver addressResolver, WorkResolver workResolver) {
        this.userResolver = userResolver;
        this.addressResolver = addressResolver;
        this.workResolver = workResolver;
    }

    @Bean
    public GraphQL graphQL() throws Exception {
        // Load the schema file
        TypeDefinitionRegistry typeRegistry = new SchemaParser()
                .parse(new InputStreamReader(getClass().getResourceAsStream("/graphql/schema.graphqls")));

        // Define a DataFetcher for the "getUser" query
        DataFetcher<User> getUserDataFetcher = dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return userResolver.getUser(id, dataFetchingEnvironment);
        };

        // Define DataFetcher for "address" in User type
        DataFetcher<Address> addressDataFetcher = dataFetchingEnvironment -> {
            User user = dataFetchingEnvironment.getSource();
            return addressResolver.getAddress(user.getId());
        };

        // Define DataFetcher for "work" in User type
        DataFetcher<Work> workDataFetcher = dataFetchingEnvironment -> {
            User user = dataFetchingEnvironment.getSource();
            return workResolver.getWork(user.getId());
        };

        // Build RuntimeWiring with all DataFetchers
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("getUser", getUserDataFetcher))
                .type("User", builder -> builder
                        .dataFetcher("address", addressDataFetcher)
                        .dataFetcher("work", workDataFetcher))
                .build();

        // Generate the schema and build the GraphQL object
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}



