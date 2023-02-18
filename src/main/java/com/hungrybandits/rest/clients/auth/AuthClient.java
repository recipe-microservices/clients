package com.hungrybandits.rest.clients.auth;

import com.hungrybandits.rest.clients.UserProxyDto;
import com.hungrybandits.rest.clients.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(
        name = "auth",
        url = "${clients.auth.url}",
        configuration = FeignClientConfiguration.class
)
public interface AuthClient {

    @GetMapping("user/me")
    ResponseEntity<UserProxyDto> getUserInSession(HttpServletRequest request);
}

