package com.spotune.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotune.demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Users findByEmail(String email);

	public Users findByPassword(String password);


}
