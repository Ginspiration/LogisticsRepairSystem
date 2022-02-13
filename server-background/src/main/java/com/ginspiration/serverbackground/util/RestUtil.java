package com.ginspiration.serverbackground.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtil {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.ui.GET_ACCESS_TOKEN_URL}")
    private String GET_ACCESS_TOKEN_URL;

    public String getWxAccessToken() {
        return restTemplate.postForEntity(GET_ACCESS_TOKEN_URL, null, String.class).getBody();
    }
}
