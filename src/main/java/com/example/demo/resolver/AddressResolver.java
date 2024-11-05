package com.example.demo.resolver;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

@Component
public class AddressResolver {

    private final RestTemplate restTemplate = new RestTemplate();

    public Address getAddress(String userId) {
//        String endpoint = "http://api.example.com/user/" + userId + "/address";
//        Map<String, Object> response = restTemplate.getForObject(endpoint, Map.class);
//
//        Address address = new Address();
//        address.setStreet((String) response.get("street"));
//        address.setCity((String) response.get("city"));
//        address.setZip((String) response.get("zip"));

        return sampleResponse();
    }

    public Address sampleResponse() {
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setZip("12345");
        return address;
    }
}

