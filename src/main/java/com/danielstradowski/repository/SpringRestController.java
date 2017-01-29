package com.danielstradowski.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.danielstradowski.service.GithubRestClient.getGithubRepository;

@RestController
public class SpringRestController {

    @RequestMapping(value = "/{owner}/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getGithubRepositoryDetails(@Qualifier("customRestTemplate") RestTemplate restTemplate, @PathVariable String owner, @PathVariable String name) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            return ResponseEntity.ok(gson.toJson(getGithubRepository(restTemplate, owner, name)));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getStatusText());
        }
    }

}