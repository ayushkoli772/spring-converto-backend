package com.example.convertobackend.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class ConversionServices {
//    @Value("$API_URL")
//    private String API_URL;
//
//    @Value("${API_KEY}")
//    private String API_KEY;

    @Autowired
    private Environment env;




    public String convert(String prev,String newFormat, String inputCode) throws JSONException, JsonProcessingException {

        String API_URL = env.getProperty("env.openrouter.api.url");
        String API_KEY = env.getProperty("env.openrouter.api.key");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        String prompt = "Convert the following code without any other explanation or additional info, even if the input code format might not be similar to expected format just convert code from " + prev + " to " + newFormat + ": " + inputCode;

        String body = "{ \"model\": \"meta-llama/llama-3.1-70b-instruct:free\", \"messages\": [{ \"role\": \"user\", \"content\": \"" + prompt + "\" }] }";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        assert API_URL != null;
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());

        // Extract the 'message.content' field
        String convertedCode = root.path("choices").get(0).path("message").path("content").asText();

        // Return only the converted code (the content)
        return convertedCode;
    }
}
