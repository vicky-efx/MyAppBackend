package com.example.demo.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

	private final String uploadDir = "C:/uploaded-images/";

//  =============================================================================================

	public String uploadImage(MultipartFile file) throws Exception {
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(uploadDir + filename);

		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return filename;
	}

//    =============================================================================================

	public List<UserImagesModel> getUserImages(int userId) {
		List<UserImagesModel> list = userImagesRepo.findByUserId(userId);

		for (UserImagesModel img : list) {
			String fullUrl = "http://localhost:8080/images/" + img.getImageUrl();
			img.setImagePath(fullUrl);
		}

		return list;
	}

}
