package com.consumer.Consumer.ServiceLayer.StepConfig;

import com.consumer.Consumer.ServiceLayer.logic.PushCommentsToCBStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    @Bean
    public PushCommentsToCBStep pushCommentsToCBStep() {
        return new PushCommentsToCBStep();
    }

}
