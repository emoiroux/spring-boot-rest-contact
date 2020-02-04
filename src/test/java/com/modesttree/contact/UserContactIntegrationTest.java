package com.modesttree.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modesttree.contact.model.UserContact;
import com.modesttree.contact.service.UserContactService;

/*
 * Test cases for API.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserContactIntegrationTest
{

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserContactService userContactService;

	private final String api = "/api/";


	/*
	 * Test ACCEPTED HTTP status.
	 * Process : post a valid message from the browser and check if HTTP status is ACCEPTED (202).
	 */

	@Test
	public void addNewContact() throws Exception
	{
		UserContact testUserContact = new UserContact(null, "new", "new@gmailcom", "555-665565");

		mockMvc.perform(post(api + "contacts", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserContact)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value()));
	}

	@Test
	public void deleteContactById() throws Exception
	{
		UserContact testUserContact = new UserContact(null, "testDel", "del@gmailcom", "777-7777");

		MvcResult ret = mockMvc.perform(post(api + "contacts", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserContact)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn();
		
		String contentAsString = ret.getResponse().getContentAsString();

		UserContact response = objectMapper.readValue(contentAsString, UserContact.class);
				
		mockMvc.perform(delete(api + "contacts/" + response.getId(), 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserContact)))
				.andExpect(status().is(HttpStatus.OK.value()));
	}

	@Test
	public void getContacts() throws Exception
	{
		MvcResult ret = mockMvc.perform(get(api + "contacts", 42L).contentType("application/json"))
				.andExpect(status().is(HttpStatus.OK.value())).andReturn();
	}
	
	@Test
	public void getContactById() throws Exception
	{
		UserContact testUserContact = new UserContact(null, "getId", "getId@gmailcom", "777-7777");

		MvcResult ret = mockMvc.perform(post(api + "contacts", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserContact)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn();
		
		String contentAsString = ret.getResponse().getContentAsString();

		UserContact response = objectMapper.readValue(contentAsString, UserContact.class);
		
		mockMvc.perform(get(api + "contacts/" + response.getId(), 42L).contentType("application/json"))
				.andExpect(status().is(HttpStatus.OK.value())).andReturn();
	}
	
	@Test
	public void updateContactById() throws Exception
	{
		UserContact testUserContact = new UserContact(null, "getId", "getId@gmailcom", "777-7777");

		MvcResult ret = mockMvc.perform(post(api + "contacts", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(testUserContact)))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn();
		
		String contentAsString = ret.getResponse().getContentAsString();

		UserContact response = objectMapper.readValue(contentAsString, UserContact.class);
		
		response.setName("newName");
		
		mockMvc.perform(put(api + "contacts/" + response.getId(), 42L).contentType("application/json")
		.content(objectMapper.writeValueAsString(response)))
				.andExpect(status().is(HttpStatus.OK.value())).andReturn();
	}
}
