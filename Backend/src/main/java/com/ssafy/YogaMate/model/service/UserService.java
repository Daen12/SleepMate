package com.ssafy.YogaMate.model.service;


import com.ssafy.YogaMate.model.dto.User;

public interface UserService {

    // 입력된 userId로 로그인 검사
    User selectUser(User user);

    // 입력된 user 객체로 회원가입
    boolean checkId(String id); // 여기서 id 중복 검사
    int signUp(User user); // 닉네임, 이메일 중복 검사, 회원가입


}
