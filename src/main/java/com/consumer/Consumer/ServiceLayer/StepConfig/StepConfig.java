package com.consumer.Consumer.ServiceLayer.StepConfig;

import com.consumer.Consumer.ServiceLayer.logic.PushCommentsToCBStep;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class StepConfig {

    @Bean
    public PushCommentsToCBStep pushCommentsToCBStep() {
        return new PushCommentsToCBStep();
    }

}
