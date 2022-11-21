package com.example.payment.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> findConverters=converters
                .stream().filter(c->c instanceof AbstractJackson2HttpMessageConverter)
                .findFirst();

        if(findConverters.isPresent()){
            AbstractJackson2HttpMessageConverter converter=(AbstractJackson2HttpMessageConverter) findConverters.get();
            converter.getObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        }
    }
    //curl -v -X POST -H "ContentType: application/json" -d '{"name":"Thuza Nwe","totalAmount":25.5,"accountNumber":"ThuzaNwe15324"}'
}
