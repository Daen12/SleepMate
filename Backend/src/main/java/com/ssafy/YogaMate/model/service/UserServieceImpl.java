package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dao.UserDao;
import com.ssafy.YogaMate.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServieceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User selectUser(User user) {
        User loginUser = userDao.selectUser(user.getId());

        if (loginUser != null && user.getPassword().equals(loginUser.getPassword())) {
            return loginUser;
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
    public int signUp(User user) {
        int inspection = userDao.getUserNickName(user.getNickname());
        // 닉네임 중복 검사
        if (inspection != 0) return 2;
        else {
            // 이메일 중복 검사
            inspection = userDao.getUserEmail(user.getEmail());
            if (inspection != 0) return 3;
            else {
                return userDao.insertUser(user);
            }
        }
    }
}
