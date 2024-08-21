package com.test.unitTesting.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.entity.User;
import com.test.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void testSaveUser() {

		User user = new User(null, "Fahad", "fahad@gmail.com", "password");
		User savedUser = userRepository.save(user);

		assertNotNull(savedUser.getId());
		assertEquals(user.getName(), savedUser.getName());
		assertEquals(user.getEmail(), savedUser.getEmail());
		assertEquals(user.getPassword(), savedUser.getPassword());
	}

	@Test
	void testFindUserById() {

		User user = new User(null, "Fahad", "fahad@gmail.com", "password");
		userRepository.save(user);
		Optional<User> savedUser = userRepository.findById(user.getId());

		assertTrue(savedUser.isPresent());
		assertEquals(user, savedUser.get());
	}

	@Test
	void testFindAllUsers() {

		User user1 = new User(null, "Fahad", "fahad@gmail.com", "password1");
		User user2 = new User(null, "Fahad17", "fahad17@gmail.com", "password2");
		userRepository.save(user1);
		userRepository.save(user2);

		List<User> users = userRepository.findAll();

		assertNotNull(users);
		assertEquals(2, users.size());
	}

	@Test
	void testDeleteUser() {

		User user = new User(null, "Fahad", "fahad@gmail.com", "password");
		User savedUser = userRepository.save(user);

		userRepository.deleteById(savedUser.getId());
		Optional<User> deletedUser = userRepository.findById(savedUser.getId());

		assertFalse(deletedUser.isPresent());
	}

}
