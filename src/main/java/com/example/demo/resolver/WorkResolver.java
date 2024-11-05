package com.example.demo.resolver;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.Work;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

@Component
public class WorkResolver {

    private final RestTemplate restTemplate = new RestTemplate();

    public Work getWork(String userId) {
//        String endpoint = "http://api.example.com/user/" + userId + "/work";
//        Map<String, Object> response = restTemplate.getForObject(endpoint, Map.class);

//        Work work = new Work();
//        work.setCompanyName((String) response.get("companyName"));
//        work.setPosition((String) response.get("position"));

        return sampleResponse();
    }

    public Work sampleResponse() {
        Work work = new Work();
        work.setCompanyName("Acme Corp");
        work.setPosition("Software Engineer");
        return work;
    }
}

