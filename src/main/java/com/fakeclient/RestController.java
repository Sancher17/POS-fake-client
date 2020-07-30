package com.fakeclient;

import com.fakeclient.model.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/")
public class RestController {

    @Value("${client.adapter.location}")
    private String sourceLocation;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "merchant/qrc-data")
    public String qrcData(@RequestBody Request request) throws Exception {
        JSONObject requestAsJson = new JSONObject(request);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpRequest = new HttpEntity<>(requestAsJson.toString(), headers);
        String fullPath = sourceLocation;
        log.info("Send request to Client Adapter, {}", fullPath);
        String responseFromClientAdapter =  restTemplate.postForObject(fullPath, httpRequest, String.class);
        log.info("Got request");
        return responseFromClientAdapter;
    }

    @PostMapping(value = "merchant/qrc-data2")
    public String qrcData2(@RequestBody String message) throws Exception {
        System.out.println(message);
        return "OK";
    }

    @GetMapping
    public String test(){
        log.info("test method called");
        return "OK";
    }
}
