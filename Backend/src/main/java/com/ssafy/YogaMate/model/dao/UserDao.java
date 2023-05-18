package com.ssafy.YogaMate.model.dao;

import com.ssafy.YogaMate.model.dto.User;

public interface UserDao {

    User selectUser(String id);
}
