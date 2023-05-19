package com.ssafy.YogaMate.controller;

import com.ssafy.YogaMate.model.dto.User;
import com.ssafy.YogaMate.model.service.UserService;
import com.ssafy.YogaMate.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "유저 컨트롤러")
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation(value = "User Login", notes = "유저 로그인")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> loginData = userService.selectUser(user);
        User loginUser = (User) loginData.get("loginUser");
        HttpStatus status = null;

        if (loginData != null) {
            result.put("access-token", (String) loginData.get("token"));
            result.put("message", SUCCESS);
            result.put("userId", loginUser.getId());
            result.put("userNickName", loginUser.getNickname());
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("message", FAIL);
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @GetMapping("/signup/id/{id}")
    @ApiOperation(value = "Duplicate Check id", notes = "유저 회원가입 전 아이디 중복 체크")
    public ResponseEntity<Map<String, Integer>> CheckId(@PathVariable String id) {
        Map<String, Integer> result = new HashMap<>();
        HttpStatus status = null;

        if (userService.checkId(id)) {
            result.put("result", 1);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("result", 0);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<Map<String, Integer>>(result, status);
    }

    @GetMapping("/signup/nickname/{nickname}")
    @ApiOperation(value = "Duplicate Check nickname", notes = "유저 회원가입 전 닉네임 중복 체크")
    public ResponseEntity<Map<String, Integer>> CheckNickname(@PathVariable String nickname) {
        Map<String, Integer> result = new HashMap<>();
        HttpStatus status = null;

        if (userService.checkNickname(nickname)) {
            result.put("result", 1);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("result", 0);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<Map<String, Integer>>(result, status);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "User Signup", notes = "유저 이메일 중복 체크 and 회원가입")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        int resNum = userService.signUp(user); // 결과 값) 1: 정상 회원가입 // 2: nickname 중복 // 3: email 중복
        HttpStatus status = null;

        if (resNum == 1) {
            result.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("message", FAIL);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
