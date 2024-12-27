package com.playground.chatbot_kab_bantul.service.impl;

import com.playground.chatbot_kab_bantul.service.WitAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class WitAiServiceImpl implements WitAiService {
    private final RestTemplate restTemplate;

    private static final String WIT_AI_API_URL = "https://api.wit.ai/message?q=";
    private static final String WIT_AI_ACCESS_TOKEN = "Bearer SDYKV5M3MZM7SVS7BBFFDAX5YZD2LQ5W";

    @Override
    public String processMessage(String message) {
        try {
            String url = WIT_AI_API_URL + URLEncoder.encode(message, StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", WIT_AI_ACCESS_TOKEN);

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            return response.getBody();
        }catch (Exception e) {
            log.error("Error processing message", e);
            return "{\"error\": \"Terjadi kesalahan saat menghubungi Wit.ai\"}";
        }
    }
}
