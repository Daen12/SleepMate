package com.ssafy.YogaMate.controller;

import com.ssafy.YogaMate.model.dto.User;
import com.ssafy.YogaMate.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        return
//    }
}
