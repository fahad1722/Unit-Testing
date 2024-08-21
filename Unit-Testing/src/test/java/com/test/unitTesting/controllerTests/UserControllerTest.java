package com.test.unitTesting.controllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.controllers.UserController;
import com.test.entity.User;
import com.test.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testCreateUser() throws Exception {

		User user = new User(null, "Fahad", "fahad@gmail.com", "password");
		User savedUser = new User(1L, "Fahad", "fahad@gmail.com", "password");

		when(userService.createUser(any(User.class))).thenReturn(savedUser);

		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.name").value("Fahad"))
				.andExpect(jsonPath("$.email").value("fahad@gmail.com"));
	}

	@Test
	void testGetUserById() throws Exception {
		User user = new User(1L, "Fahad", "fahad@gmail.com", "password");

		when(userService.getUserById(1L)).thenReturn(Optional.of(user));

		mockMvc.perform(get("/api/users/1", 1L)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.name").value("Fahad")).andExpect(jsonPath("$.email").value("fahad@gmail.com"));
	}

	@Test
	void testGetAllUsers() throws Exception {
		User user1 = new User(1L, "Fahad", "fahad@gmail.com", "password");
		User user2 = new User(2L, "Fahad17", "fahad17@gmail.com", "password2");

		List<User> users = Arrays.asList(user1, user2);

		when(userService.getAllUsers()).thenReturn(users);

		mockMvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].id").value(1L)).andExpect(jsonPath("$[0].name").value("Fahad"))
				.andExpect(jsonPath("$[1].id").value(2L)).andExpect(jsonPath("$[1].name").value("Fahad17"));
	}

	@Test
	void testDeleteUser() throws Exception {
		User user = new User(1L, "Fahad", "fahad@gmail.com", "password");

		when(userService.getUserById(1L)).thenReturn(Optional.of(user));

		mockMvc.perform(delete("/api/users/1", 1L)).andExpect(status().isOk());
	}
}
