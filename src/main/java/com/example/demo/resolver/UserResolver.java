package com.example.demo.resolver;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.Work;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

@Component
public class UserResolver {

    private final RestTemplate restTemplate = new RestTemplate();

    public User getUser(String id, DataFetchingEnvironment env) {
//        String endpoint = "http://api.example.com/user/" + id;
//        Map<String, Object> response = restTemplate.getForObject(endpoint, Map.class);
//
//        User user = new User();
//        user.setId(id);
//        user.setName((String) response.get("name"));
//        user.setEmail((String) response.get("email"));
//        user.setPhone((String) response.get("phone"));

        User user = sampleResponse();

        // Use the selection set to decide if nested fields (address, work) need further resolution
        if (env.getSelectionSet().contains("address")) {
            user.setAddress(new Address()); // Placeholder for further resolution by AddressResolver
        }
        if (env.getSelectionSet().contains("work")) {
            user.setWork(new Work()); // Placeholder for further resolution by WorkResolver
        }

        return user;
    }

    public User sampleResponse() {
        User user = new User();
        user.setId("123");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("123-456-7890");
        return user;
    }
}

