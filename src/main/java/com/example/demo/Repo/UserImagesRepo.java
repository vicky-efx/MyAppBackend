package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.UserImagesModel;

@Repository
public interface UserImagesRepo extends JpaRepository<UserImagesModel, Integer>{

    List<UserImagesModel> findByUserId(int userId);

}
