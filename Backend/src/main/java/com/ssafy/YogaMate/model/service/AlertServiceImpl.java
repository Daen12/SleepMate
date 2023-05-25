package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dao.AlertDao;
import com.ssafy.YogaMate.model.dto.Alert;
import com.ssafy.YogaMate.model.dto.AlertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    @Autowired
    AlertDao alertDao;

    @Override
    public boolean writeAlert(Alert alert) {
        return alertDao.writeAlert(alert);
    }

    @Override
    public List<AlertDto> getAllAlerts(String nickname) {
        return alertDao.getAllAlerts(nickname);
    }

    @Override
    public boolean deleteOneAlert(int idx) {
        return alertDao.deleteOneAlert(idx);
    }

    @Override
    public boolean deleteAllAlert(String nickname) {
        return alertDao.deleteAllAlert(nickname);
    }

    @Override
    public boolean deleteArticleAlert(String nickname, String articleIdx) {
        return alertDao.deleteArticleAlert(nickname, articleIdx);
    }


}
