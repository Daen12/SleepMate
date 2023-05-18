package com.ssafy.YogaMate.model.service;


import com.ssafy.YogaMate.model.dto.User;

public interface UserService {

    // 입력된 userId로 로그인 검사
    User selectUser(User user);
}
