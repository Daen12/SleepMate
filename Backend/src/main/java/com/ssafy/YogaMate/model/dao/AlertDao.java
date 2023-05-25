package com.ssafy.YogaMate.model.dao;

import com.ssafy.YogaMate.model.dto.Alert;
import com.ssafy.YogaMate.model.dto.AlertDto;

import java.util.List;

public interface AlertDao {
    boolean writeAlert(Alert alert);

    List<AlertDto> getAllAlerts(String nickname);

    boolean deleteOneAlert(int idx);

    boolean deleteAllAlert(String nickname);

    boolean deleteArticleAlert(String nickname, String articleIdx);
}
