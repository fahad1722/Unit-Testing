package com.test.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.mockito.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
