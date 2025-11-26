package com.example.demo.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.LoginDTO;
import com.example.demo.Model.UserModel;
import com.example.demo.Model.UserResponseDTO;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public UserResponseDTO login(@RequestBody LoginDTO req) throws Exception {
	    return userService.login(req.email, req.password);
	}


	// GET ALL USERS
	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}

	// GET USER BY ID
	@GetMapping("/users/{id}")
	public UserModel getUserById(@PathVariable int id) throws Exception {
		return userService.getUserById(id);
	}
}
