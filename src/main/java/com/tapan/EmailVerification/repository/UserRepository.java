package com.tapan.EmailVerification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapan.EmailVerification.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);

}
