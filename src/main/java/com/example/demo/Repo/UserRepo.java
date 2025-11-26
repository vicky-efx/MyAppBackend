package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.UserModel;


@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer>{

	UserModel findByEmail(String email);


}
