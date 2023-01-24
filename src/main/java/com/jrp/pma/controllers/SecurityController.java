package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.UserRepository;
import com.jrp.pma.entities.UserAccount;


@Controller
public class SecurityController {

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	UserRepository accountRepo;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount",userAccount);
		
		return "security/register";
		
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		//grab, encrypt, and save user password with new user account
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRepo.save(user);
		return "redirect:/";
	}
}
