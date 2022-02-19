package com.ginspiration.serverbackground.util;

import com.ginspiration.serverbackground.entity.RepairedToUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestUtil {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.ui.GET_ACCESS_TOKEN_URL}")
    private String GET_ACCESS_TOKEN_URL;
    @Value("${wechat.ui.SEND_REPAIRED_TO_USER}")
    private String SEND_REPAIRED_TO_USER;

    public String getWxAccessToken() {
        return restTemplate.postForEntity(GET_ACCESS_TOKEN_URL, null, String.class).getBody();
    }
    public String sendRepairedToUser(RepairedToUser repairedToUser){
        log.info("sendToUser:{}",SEND_REPAIRED_TO_USER);
        return restTemplate.postForObject(SEND_REPAIRED_TO_USER, repairedToUser, String.class);
    }
}
