package com.hungrybandits.rest.clients.user;

import com.hungrybandits.rest.clients.UserProxyDto;
import com.hungrybandits.rest.clients.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "user",
        url = "${clients.user.url}",
        configuration = FeignClientConfiguration.class
)
public interface UserClient {

    @GetMapping("user/{userId}")
    ResponseEntity<UserProxyDto> getUser(Long userId);
}
