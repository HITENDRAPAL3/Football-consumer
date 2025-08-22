package com.consumer.Consumer.GatewayLayer.config;

import com.consumer.Consumer.GatewayLayer.implementation.PushEventsDelegate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class DelegateConfig {

    @Bean
    public PushEventsDelegate pushEventsDelegate() {
        return new PushEventsDelegate();
    }

}
