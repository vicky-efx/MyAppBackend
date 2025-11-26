package com.example.demo.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserImagesRepo userImagesRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    
    @PostMapping("/admin/send-image-multiple")
    public String sendImageToMultipleUsers(
            @RequestParam("userIds") List<Integer> userIds,
            @RequestParam("file") MultipartFile file) throws Exception {

        String fileName = imageService.uploadImage(file);

        for (Integer userId : userIds) {
            var user = userService.getUserById(userId);

            UserImagesModel img = new UserImagesModel();
            img.setUser(user);
            img.setImageUrl(fileName);
            userImagesRepo.save(img);

            messagingTemplate.convertAndSend("/topic/user/" + userId, "NEW_IMAGE");
        }

        return "Image sent successfully to " + userIds.size() + " users!";
    }

    @GetMapping("/user/images")
    public List<UserImagesModel> getUserImages(@RequestParam("userId") int userId) {
        return imageService.getUserImages(userId);
    }

}

