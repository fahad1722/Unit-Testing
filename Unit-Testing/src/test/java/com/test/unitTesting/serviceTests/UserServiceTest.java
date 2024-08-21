package com.test.unitTesting.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;

//@ExtendWith(MockitoExtension.class):::: Replaces MockitoAnnotations.openMocks(this) for initializing mocks.
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllUsers() {

		User user1 = new User(1L, "Fahad", "fahad@gmail.com", "password123");
		User user2 = new User(2L, "Fahad17", "fahad17@gmail.com", "password123");

		List<User> users = Arrays.asList(user1, user2);
		when(userRepository.findAll()).thenReturn(users);

		List<User> foundUsers = userService.getAllUsers();

		assertEquals(2, foundUsers.size());
		assertEquals(user1.getName(), foundUsers.get(0).getName());
		assertEquals(user2.getName(), foundUsers.get(1).getName());
		assertEquals(users, foundUsers);
		verify(userRepository).findAll();
	}

	@Test
	void testGetUserById() {

		Long userId = 1L;
		User user = new User(userId, "Fahad", "fahad@gmail.com", "password");

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		Optional<User> foundUser = userService.getUserById(1L);

		assertEquals(user.getId(), foundUser.get().getId());
		assertEquals(user.getName(), foundUser.get().getName());
		assertTrue(foundUser.isPresent());

		verify(userRepository).findById(userId);
	}

	@Test
	void testCreateUser() {

		User user = new User(null, "Fahad", "fahad@gmail.com", "password");
		User savedUser = new User(1L, "Fahad", "fahad@gmail.com", "password");

		when(userRepository.save(user)).thenReturn(savedUser);

		User createdUser = userService.createUser(user);

		assertNotNull(createdUser);
		assertEquals(savedUser, createdUser);
		assertEquals(savedUser.getId(), createdUser.getId());
		assertEquals(savedUser.getName(), createdUser.getName());
		assertEquals(savedUser.getEmail(), createdUser.getEmail());
		assertEquals(savedUser.getPassword(), createdUser.getPassword());

		verify(userRepository).save(user);
	}

	@Test
	void testDeleteUser() {
		Long userId = 1L;
		doNothing().when(userRepository).deleteById(userId);

		userService.deleteUser(userId);

		verify(userRepository).deleteById(userId);
	}
}
