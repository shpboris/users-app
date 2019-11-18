package org.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.users.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
