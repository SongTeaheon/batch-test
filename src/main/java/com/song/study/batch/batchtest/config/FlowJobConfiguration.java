package com.song.study.batch.batchtest.config;

import org.springframework.batch.core.ExitStatus;
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
        return jobBuilderFactory.get("flowJob6")
                .start(flowStep1())
                    .on("FAILED")
                    .to(flowStep2())
                    .on("FAILED")
                    .stop()
                .from(flowStep1())
                    .on("*")
                    .to(flowStep3())
                    .next(flowStep4())
                .from(flowStep2())
                    .on("*")
                    .to(flowStep5())
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
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
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
                    contribution.setExitStatus(ExitStatus.FAILED);
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

    @Bean
    public Step flowStep4() {
        return stepBuilderFactory.get("flowStep4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================");
                    System.out.println("Hello Spring Batch4");
                    System.out.println("===================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step flowStep5() {
        return stepBuilderFactory.get("flowStep5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================");
                    System.out.println("Hello Spring Batch5");
                    System.out.println("===================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

