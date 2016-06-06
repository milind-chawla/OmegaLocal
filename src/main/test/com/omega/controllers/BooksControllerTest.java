package com.omega.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.omega.config.OmegaCoreConfig;
import com.omega.config.OmegaWebApplicationConfig;
import com.omega.config.OmegaWebApplicationInitializer;

import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(classes = {OmegaCoreConfig.class}),
	@ContextConfiguration(classes = {OmegaWebApplicationConfig.class}),
	@ContextConfiguration(classes = {OmegaWebApplicationInitializer.class}),
})
public class BooksControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getBook() throws Exception {
		mockMvc.perform(get("/books/1.json")
				.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.id").value(1))
					.andExpect(jsonPath("$.name").value("Book 1"));
	}
}
