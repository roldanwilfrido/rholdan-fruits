package com.rholdan.fruits.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class InternalStoreConfig {

    @Value("${store.internal.username}")
    private String username;

    @Value("${store.internal.password}")
    private String password;

}
