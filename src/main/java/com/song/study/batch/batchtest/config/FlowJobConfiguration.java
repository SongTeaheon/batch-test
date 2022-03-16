package com.song.study.batch.batchtest.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob2")
                .start(flowStep1())
                .on("COMPLETED").to(flowStep2())
                .from(flowStep1())
                .on("FAILED").to(flowStep3())
                .end()
                .build();
    }


    @Bean
    public Step flowStep1() {
        return stepBuilderFactory.get("flowStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================");
                    System.out.println("Hello Spring Batch1");
                    System.out.println("===================");
                    throw new RuntimeException();
                })
                .build();
    }

    @Bean
    public Step flowStep2() {
        return stepBuilderFactory.get("flowStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================");
                    System.out.println("Hello Spring Batch2");
                    System.out.println("===================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step flowStep3() {
        return stepBuilderFactory.get("flowStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================");
                    System.out.println("Hello Spring Batch3");
                    System.out.println("===================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

