package com.example.demo.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.UserImagesModel;
import com.example.demo.Repo.UserImagesRepo;
import com.example.demo.Service.UserImageService;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserImageController {

	@Autowired
	private UserImageService imageService;

//  =============================================================================================

	@PostMapping("/admin/send-image-multiple")
	public String sendImageToMultipleUsers(@RequestParam List<Integer> userIds, @RequestParam MultipartFile file)
			throws Exception {

		return imageService.uploadImage(userIds, file);
	}

//  =============================================================================================

	@GetMapping("/user/images")
	public List<UserImagesModel> getUserImages(@RequestParam("userId") int userId) {
	    return imageService.getUserImages(userId);
	}


}
