package com.apress.webfluxdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootWebfluxFunctionalDemoApplicationTests {

	@Value("${sample.users.count}")
	private int sampleUserCount;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void getAllUsers() {
		webTestClient.get().uri("/api/users").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBodyList(User.class)
				.consumeWith(result -> assertEquals(sampleUserCount, result.getResponseBody().size()));
	}

}
