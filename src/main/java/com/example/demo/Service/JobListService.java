package com.example.demo.Service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.JobList;

@Service
public class JobListService {

    private final RestTemplate restTemplate;

    public JobListService() {
        this.restTemplate = new RestTemplate();
    }

    public List<JobList> getJobListFromAPI(String apiUrl) {
        ResponseEntity<List<JobList>> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<JobList>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Handle error cases, e.g., throw an exception or return null
            return null;
        }
    }

    public JobList getJobFromAPI(String apiUrl) {
        return restTemplate.getForObject(apiUrl, JobList.class);
    }
}

