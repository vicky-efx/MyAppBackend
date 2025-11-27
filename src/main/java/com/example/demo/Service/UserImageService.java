package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.UserImagesModel;
import com.example.demo.Repo.UserImagesRepo;

@Service
public class UserImageService {

	@Autowired
	private UserImagesRepo userImagesRepo;

//  =============================================================================================

	public String uploadImage(List<Integer> userIds, MultipartFile file) throws Exception {

	    String base64 = Base64.getEncoder().encodeToString(file.getBytes());
	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

	    UserImagesModel img = new UserImagesModel();
	    img.setUserIds(userIds);
	    img.setBase64Image(base64);
	    img.setImageName(fileName);
	    img.setCreatedAt(LocalDateTime.now());

	    userImagesRepo.save(img);

	    return "Image saved for users: " + userIds;
	}


//    =============================================================================================

	public List<UserImagesModel> getUserImages(int userId) {
	    return userImagesRepo.findByUserIdsContaining(userId);
	}


}
