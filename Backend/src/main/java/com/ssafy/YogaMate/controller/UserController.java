package com.ssafy.YogaMate.controller;

import com.ssafy.YogaMate.model.dto.User;
import com.ssafy.YogaMate.model.service.UserService;
import com.ssafy.YogaMate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        User loginUser = userService.selectUser(user);
        HttpStatus status = null;

        try {
            if (loginUser != null) {
                result.put("access-token", jwtUtil.createToken("id", user.getId()));
                result.put("message", "success");
                result.put("userId", loginUser.getId());
                result.put("userNickName", loginUser.getNickName());
                status = HttpStatus.ACCEPTED;
            } else {
                result.put("message", "fail");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("message", "fail");
            status = HttpStatus.NO_CONTENT;
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
