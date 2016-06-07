package com.appoie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppoieApplication.class)
@WebAppConfiguration
@Profile(value="teste")
public class AppoieApplicationTests {

	@Test
	public void contextLoads() {
	}

}
