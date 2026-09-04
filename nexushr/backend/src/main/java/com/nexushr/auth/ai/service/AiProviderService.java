package com.nexushr.auth.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AiProviderService {
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    @Value("${ai.provider.url:}")
    private String providerUrl;

    @Value("${ai.provider.api-key:}")
    private String apiKey;

    @Value("${ai.provider.model:gpt-4o-mini}")
    private String model;

    public AiProviderService(ObjectMapper objectMapper, RestClient.Builder restClientBuilder) {
        this.objectMapper = objectMapper;
        this.restClient = restClientBuilder.build();
    }

    public String complete(String systemPrompt, String userPrompt) {
        if (providerUrl == null || providerUrl.isBlank() || apiKey == null || apiKey.isBlank()) {
            return null;
        }

        Map<String, Object> request = Map.of(
                "model", model,
                "temperature", 0.2,
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userPrompt)));
        try {
            String body = restClient.post()
                    .uri(providerUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + apiKey)
                    .body(request)
                    .retrieve()
                    .body(String.class);
            JsonNode root = objectMapper.readTree(body);
            JsonNode content = root.path("choices").path(0).path("message").path("content");
            return content.isTextual() ? content.asText() : null;
        } catch (RuntimeException | java.io.IOException exception) {
            return null;
        }
    }
}