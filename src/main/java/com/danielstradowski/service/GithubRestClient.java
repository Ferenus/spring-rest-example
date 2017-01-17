package com.danielstradowski.service;

import com.danielstradowski.model.ResponseObject;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class GithubRestClient {

    private static final String GITHUB_ENDPOINT_URL = "api.github.com/repos/";

    public static ResponseObject getGithubRepository(RestTemplate restTemplate, String owner, String name) {
        StringBuilder builder = new StringBuilder("https://");
        builder.append(GITHUB_ENDPOINT_URL);
        builder.append(owner);
        builder.append("/");
        builder.append(name);

        URI uri = URI.create(builder.toString());
        return restTemplate.getForObject(uri, ResponseObject.class);
    }
}
