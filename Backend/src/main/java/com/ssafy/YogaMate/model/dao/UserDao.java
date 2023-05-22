package com.ssafy.YogaMate.model.dao;

import com.ssafy.YogaMate.model.dto.User;

public interface UserDao {

    // 로그인
    User selectUser(String id);

    // 회원가입
    int getUserId(String id);
    int getUserNickName(String nickname);
    int insertUser(User user);

    // 회원의 선호 키워드 갱신
    boolean updatePrefer(User user);
}
