package com.spotune.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotune.demo.entity.Users;
import com.spotune.demo.repository.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService {
	@Autowired
	UsersRepository repo;
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "user added successfully";
	}
	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null)
			return false;
		else
			return true;
	}
	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		if(user==null)
			return false; 
		else {
			String db_password=user.getPassword();
			if(password.equals(db_password))
				return true;
			else
				return false;
		}
		
	}
	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		return user.getRole();
	}
	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
	}
	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
	}

}
