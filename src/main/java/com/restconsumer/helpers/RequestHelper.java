package com.restconsumer.helpers;

import com.restconsumer.models.AuthResponse;
import com.restconsumer.models.Item;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public final class RequestHelper {

    public static AuthResponse getAuth() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{ \"apiKey\": \"23567b218376f79d9415\" }";

        HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

        ResponseEntity<AuthResponse> exchange =
                restTemplate.exchange("http://interview.agileengine.com/auth", HttpMethod.POST, entity, AuthResponse.class);
        return exchange.getBody();
    }

    public static HashMap getImages(String token) {
        HashMap result = new HashMap();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        boolean hasMore = true;
        int page = 1;


        ResponseEntity<Object> exchange = null;

        while (hasMore) {
            exchange = restTemplate.exchange("http://interview.agileengine.com/images" + "?page=" + page, HttpMethod.GET, entity, Object.class);

            if (exchange.getBody() != null) {
                Map body = (Map) exchange.getBody();

                result.put(body.get("page"), body.get("pictures"));

                hasMore = (boolean) body.get("hasMore");
                page++;
            }
        }
        return result;
    }

    public static Item getImageInfo(String id, String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Item> item =
                restTemplate.exchange("http://interview.agileengine.com/images" + "/" + id, HttpMethod.GET, entity, Item.class);

        return item.getBody();
    }
}
