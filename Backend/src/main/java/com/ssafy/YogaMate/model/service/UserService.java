package com.ssafy.YogaMate.model.service;


import com.ssafy.YogaMate.model.dto.User;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface UserService {

    // 입력된 userId로 로그인 검사
    Map<String, Object> selectUser(User user) throws UnsupportedEncodingException;

    // 입력된 user 객체로 회원가입
    boolean checkId(String id); // 여기서 id 중복 검사
    boolean checkNickname(String nickname); // 여기서 nickname 중복 검사
    int signUp(User user); // 닉네임, 이메일 중복 검사, 회원가입

    // gpt 설문으로 선호하는 키워드를 유저 데이터에 저장
    boolean updatePrefer(User user);
}
