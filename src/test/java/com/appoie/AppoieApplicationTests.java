package com.appoie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppoieApplication.class)
@WebAppConfiguration
public class AppoieApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(AppoieApplication.class, args);
	}
	
	@Test
	public void contextLoads() {
		
	}

}
