package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dao.UserDao;
import com.ssafy.YogaMate.model.dto.User;
import com.ssafy.YogaMate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServieceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Map<String, Object> selectUser(User user) throws UnsupportedEncodingException {
        Map<String, Object> loginData = new HashMap<>();
        User loginUser = userDao.selectUser(user.getId());
        loginData.put("loginUser", loginUser);

        if (loginUser != null && user.getPassword().equals(loginUser.getPassword())) {
            String token = jwtUtil.createToken("id", user.getId());
            loginData.put("token", token);
            return loginData;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkId(String id) {
        int num = userDao.getUserId(id);
        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkNickname(String nickname) {
        int num = userDao.getUserNickName(nickname);
        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int signUp(User user) {
        return userDao.insertUser(user);
    }
}

