package com.antospa.rest.item.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {
    @Value("${mongo.expire}")
    public Integer expireTime;
}
