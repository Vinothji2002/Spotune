package com.spotune.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spotune.demo.entity.Song;
import com.spotune.demo.entity.Users;
import com.spotune.demo.service.SongService;
import com.spotune.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	@PostMapping("/registration")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false) {
			service.addUser(user);
			System.out.println("user aadded");
		} 
		else
			System.out.println("user already exist");
		return "login";
	}
	
	@PostMapping("/validate")
	public String validate(@ModelAttribute Users users,HttpSession session,Model model) {
		String email=users.getEmail();
		String password=users.getPassword();
		if(service.validateUser(email,password)==true) {
			
			String role=service.getRole(email);
			session.setAttribute("email",email);	
			if(role.equals("admin"))
				return "adminHome";
			else {
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songsList = songService.fetchAllSongs();
				model.addAttribute("songs", songsList);
				model.addAttribute("isPremium", userStatus);
				System.out.println(userStatus);
				return "customerHome";
			}
			
		}
		else
			return "login";
	}
/*	@GetMapping("/pay")
	public String pay(@RequestParam String email) {
		boolean paymentStatus=false;
		//payment api
		if(paymentStatus==true) {
			Users user=service.getUser(email);
			user.setPassword(true);
			service.updateUser(user);
		}
		
		return "login";
	}
*/
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
//	@Autowired
//	UsersService service;
//	@PostMapping("/register")
//	public String addUsers(@ModelAttribute Users user) {
//		boolean userStatus=service.userExist(user.getEmail());
//		if(userStatus==true) {
//			System.out.println("User already exist");
//			return "home";
//		}
//		else {
//			service.addUsers(user);
//			System.out.println("User added successfully");
//			return "home";
//		}
//	}
//	
//	@GetMapping("/validate")
//	public String validate(@ModelAttribute Users user) {
//		if(service.checkCredentials(user.getEmail(),user.getPassword(),user)) {
//			String role=service.findUserRole(user.getEmail());
//			if(role.equals("admin")) {
//				return "adminHome";
//			}
//			else
//				return "customerHome";
//			
//		}
//		else {
//			System.out.println("Invalid Credentials");
//			return "home";
//		}
//			
//	}
	
}
