package com.danielstradowski.repository;

import com.danielstradowski.model.ResponseObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringRestControllerTest {

    @InjectMocks
    private SpringRestController springRestController = new SpringRestController();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnDataWhenRepositoryFound() {
        // Given
        String owner = "Ferenus";
        String name = "vampire";

        // When
        when(restTemplate.getForObject(anyObject(), eq(ResponseObject.class))).thenReturn(new ResponseObject("Ferenus/vampire", null, "https://github.com/Ferenus/vampire.git", 0, "2016-07-17T17:18:57Z"));
        ResponseEntity<String> response = springRestController.getGithubRepositoryDetails(restTemplate, owner, name);

        // Then
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody(), "{\"full_name\":\"Ferenus/vampire\",\"description\":null,\"clone_url\":\"https://github.com/Ferenus/vampire.git\",\"stargazers_count\":0,\"created_at\":\"2016-07-17T17:18:57Z\"}");
    }

    @Test
    public void shouldReturnHttp404WhenRepositoryNotFound() {
        // Given
        String owner = "test";
        String name = "";

        // When
        when(restTemplate.getForObject(anyObject(), eq(ResponseObject.class))).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        ResponseEntity<String> response = springRestController.getGithubRepositoryDetails(restTemplate, owner, name);

        // Then
        assertEquals(response.getStatusCodeValue(), 404);
        assertEquals(response.getBody(), "NOT_FOUND");
    }

    @Test
    public void shouldReturnHttp500WhenGithubNotResponded() {
        // Given
        String owner = "Ferenus";
        String name = "vampire";

        // When
        when(restTemplate.getForObject(anyObject(), eq(ResponseObject.class))).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<String> response = springRestController.getGithubRepositoryDetails(restTemplate, owner, name);

        // Then
        assertEquals(response.getStatusCodeValue(), 500);
        assertEquals(response.getBody(), "INTERNAL_SERVER_ERROR");
    }
}