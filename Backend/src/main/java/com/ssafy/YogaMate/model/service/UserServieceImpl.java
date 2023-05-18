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
}
