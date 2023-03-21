package com.example.SpringBatchTutorial.job.JobListener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * desc : Job Listener Tasklet 출력
 * run  : --spring.batch.job.names=jobListenerJob
 * */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobListenerConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job jobListenerJob(Step jobListenerStep) {
		return jobBuilderFactory.get("jobListenerJob")
							    .incrementer(new RunIdIncrementer())
							    .listener(new JobLoggerListener())
							    .start(jobListenerStep)
							    .build();
	}
	
	@JobScope
	@Bean
	public Step jobListenerStep(Tasklet jobListenerTasklet) {
		return stepBuilderFactory.get("jobListenerStep")
								 .tasklet(jobListenerTasklet)
								 .build();
	}

	@StepScope
	@Bean
	public Tasklet jobListenerTasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, 
										ChunkContext chunkContext) throws Exception {
				// 성공 테스트
				// log.info("Job Listener Tasklet");
				// return RepeatStatus.FINISHED;
				
				// 실패 테스트
				throw new Exception("Job is Failed");
			}
		};
	}
	
}