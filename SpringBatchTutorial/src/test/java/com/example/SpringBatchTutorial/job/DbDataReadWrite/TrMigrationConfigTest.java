package com.example.SpringBatchTutorial.job.DbDataReadWrite;

import javax.batch.runtime.JobExecution;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.SpringBatchTutorial.job.HelloWorld.HelloWorldJobConfig;
import com.example.SpringBatchTutorial.job.HelloWorld.SpringBatchTestConfig;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBatchTest
// @SpringBootTest(classes = {SpringBatchTestConfig.class, TrMigrationConfig.class})
public class TrMigrationConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Test()
	public void success_noData() throws Exception{
		// when
		JobExecution excution = jobLauncherTestUtils.launchJob();
		
		// then 
		Assertions.assertEquals(excution.getExitStatus(), ExitStatus.COMPLETED);
		Assertions.assertEquals(0, accountsRepository.count());
		
	}
	
	@Test()
	public void success_existData() throws Exception{
		// given
		Orders orders1 = new Order(null, "kakao gift", 15000, new Date());
		Orders orders2 = new Order(null, "naver gift", 15000, new Date());
		
		ordersRepository.save(orders1);
		ordersRepository.save(orders2);
		
		// when
		JobExecution excution = jobLauncherTestUtils.launchJob();
		
		// then 
		Assertions.assertEquals(excution.getExitStatus(), ExitStatus.COMPLETED);
		Assertions.assertEquals(2, accountsRepository.count());
		
	}
	
	@AfterEach
	public void cleanUpEach() {
		ordersRepository.deleteAll();
		accountsRepository.deleteAll();
	}
	
}
