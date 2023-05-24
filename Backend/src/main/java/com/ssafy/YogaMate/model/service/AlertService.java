package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dto.Alert;

import java.util.List;

public interface AlertService {

    boolean writeAlert(Alert alert);

    List<Alert> getAllAlerts(String nickname);

    boolean deleteOneAlert(int idx);

    boolean deleteAllAlert(String nickname);

    boolean deleteArticleAlert(String nickname, String articleIdx);
}
