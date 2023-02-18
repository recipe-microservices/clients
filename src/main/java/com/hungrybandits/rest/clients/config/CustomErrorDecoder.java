package com.hungrybandits.rest.clients.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hungrybandits.rest.exceptions.RestException;
import com.hungrybandits.rest.exceptions.dtos.ApiCallError;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        ApiCallError apiCallError = null;
        try {
            String json = IOUtils.toString(response.body().asInputStream(), "UTF-8");
            apiCallError = new ObjectMapper().readValue(json, ApiCallError.class);
        } catch (IOException e) {
            return new RestException(new ApiCallError(HttpStatus.INTERNAL_SERVER_ERROR.value(), requestUrl, e.getMessage(), LocalDateTime.now(), null));
        }
        return new RestException(apiCallError);
    }
}