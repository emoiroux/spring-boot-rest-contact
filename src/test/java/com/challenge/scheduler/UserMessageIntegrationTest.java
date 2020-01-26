package com.challenge.scheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.scheduler.model.UserMessage;
import com.challenge.scheduler.service.UserMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Test cases for API V1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserMessageIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserMessageService userMessageService;

	private final String apiV1 = "/api/v1/";

	/*
	 * Test if message is printed X second after now. Schedule a message and check
	 * if it appear in the console.
	 */

	@Test
	public void postMessageAndCheckConsole() throws Exception {

		int timeToAddS = 3;
		int consoleTimeOutMS = 7000;

		// Schedule a message 'timeToAddS' second after now.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, timeToAddS);

		UserMessage testUserMessage = new UserMessage(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), "testMessage3");

		// Override the PrintStream to check if message is received.
		SearchPrintStream customPrintStream = new SearchPrintStream(System.out, testUserMessage.getMessage());
		System.setOut(customPrintStream);

		mockMvc.perform(post(apiV1 + "messages/message").contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserMessage)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn();

		// Wait at most 'consoleTimeOutMS'
		Awaitility.await().atMost(consoleTimeOutMS, TimeUnit.MILLISECONDS).until(customPrintStream::isFind);

		assertTrue(customPrintStream.isFind());
	}

	/*
	 * Test ACCEPTED Http status. Post a valid message from the browser and check if
	 * Http status is ACCEPTED (202).
	 */

	@Test
	public void postMessageAndCheckIfAcceptedResponse() throws Exception {

		UserMessage testUserMessage = new UserMessage(12, 1, 3, "testMessage");

		mockMvc.perform(post(apiV1 + "messages/message", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserMessage)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value()));
	}

	/*
	 * Test BAD_REQUEST Http status with invalid hour. Post a not valid message from
	 * the browser and check if Http status come back with BAD_REQUEST (400).
	 */

	@Test
	public void postBadHourMessage() throws Exception {
		UserMessage testUserMessage = new UserMessage(60, 2, 3, "testMessage2");

		mockMvc.perform(post(apiV1 + "messages/message", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserMessage)))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

	}

	/*
	 * Test BAD_REQUEST Http status with empty message. Post a empty message from
	 * the browser and check if Http status come back with BAD_REQUEST (400).
	 */

	@Test
	public void postEmptyMessage() throws Exception {
		UserMessage testUserMessage = new UserMessage(59, 2, 3, "");

		mockMvc.perform(post(apiV1 + "messages/message", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserMessage))).andExpect(status().is(400));

	}

	/*
	 * Test userMessageService integrity. Save and retrieve the message by ID.
	 */

	@Test
	public void saveMessageWithServiceAndCheckIfServiceFindById() throws Exception {
		UserMessage testUserMessage = new UserMessage(6, 2, 3, "testMessage2");

		UserMessage UserMessageEntity = userMessageService.save(testUserMessage);

		Optional<UserMessage> userMessageEntity = userMessageService.findById(UserMessageEntity.getId());

		assertThat(userMessageEntity.get()).isNotNull();
	}

	class SearchPrintStream extends PrintStream {
		private String message;
		private boolean find;

		public SearchPrintStream(OutputStream out, String message) {
			super(out);
			this.message = message;
		}

		public void println(String s) {
			super.println(s);
			if (s.equals(this.message))
				find = true;
		}

		public boolean isFind() {
			return find;
		}
	}

}
