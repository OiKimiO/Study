package com.example.SpringBatchTutorial;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

	@Autowired
	private Job helloWorldJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Scheduled(cron="0 */1 * * * *")
	public void helloWorldJobRun() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParameters(
					Collections.singletonMap("requestItem", new JobParameter(System.currentTimeMillis()))
				);
		
		jobLauncher.run(helloWorldJob, jobParameters);
	}
}
