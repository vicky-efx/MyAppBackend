package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.UserModel;
import com.example.demo.Model.UserResponseDTO;
import com.example.demo.Repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public UserResponseDTO login(String email, String password) throws Exception {

	    UserModel user = userRepo.findByEmail(email);
	    if (user == null) {
	        throw new Exception("User not found with this email!");
	    }

	    String dbPassword = user.getPassword();

	    if (!dbPassword.equals(password)) {
	        throw new Exception("Invalid password!");
	    }

	    return new UserResponseDTO(
	        user.getId(),
	        user.getName(),
	        user.getEmail(),
	        user.getRole()
	    );
	}


	public UserModel getUserById(int userId) throws Exception {
		return userRepo.findById(userId).orElseThrow(() -> new Exception("User not found with ID: " + userId));
	}

	public List<UserModel> getAllUsers() {
		return userRepo.findAll();
	}

}
