package com.song.study.batch.batchtest.config.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("ExecutionContextTasklet3 was executed");
        ExecutionContext jobExecutionContext =
                contribution.getStepExecution().getJobExecution().getExecutionContext();
        if (jobExecutionContext.get("name") == null) {
            jobExecutionContext.put("name", "user1");
            throw new RuntimeException("ExecutionContextTasklet3 exception");
        }

        System.out.println(jobExecutionContext.get("name"));

        return RepeatStatus.FINISHED;
    }
}
