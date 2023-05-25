package com.ssafy.YogaMate.controller;

import com.ssafy.YogaMate.model.dto.Alert;
import com.ssafy.YogaMate.model.dto.AlertDto;
import com.ssafy.YogaMate.model.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alert")
@Api(tags = "알림 컨트롤러")
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping("/write")
    @ApiOperation(value = "Create Alert Data", notes = "알림 데이터 생성")
    public ResponseEntity<?> writeAlert(@RequestBody Alert alert) {
        if (alertService.writeAlert(alert)) {
            return new ResponseEntity<Alert>(alert, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{nickname}")
    @ApiOperation(value = "Get Users Alert", notes = "로그인 한 유저의 알림 데이터 반환")
    public ResponseEntity<Map<String, Object>> getAllAlerts(@PathVariable String nickname) {
        Map<String, Object> result = new HashMap<>();
        List<AlertDto> alertList = alertService.getAllAlerts(nickname);
        HttpStatus status = null;

        if (alertList == null && alertList.size() == 0) {
            result.put("AlertNum", 0);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("Alerts", alertList);
            result.put("AlertNum", alertList.size());
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @DeleteMapping("/delete/one/{idx}")
    @ApiOperation(value = "Delete One Alert", notes = "로그인 유저 알람 모달에서 하나씩 지우기")
    public ResponseEntity<?> deleteOneAlert(@PathVariable int idx) {
        if (alertService.deleteOneAlert(idx)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/all/{nickname}")
    @ApiOperation(value = "Delete All Alert", notes = "알람 모달에서 모두 지우기")
    public ResponseEntity<?> deleteAllAlert(@PathVariable String nickname) {
        if (alertService.deleteAllAlert(nickname)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{nickname}/{articleIdx}")
    @ApiOperation(value = "Delete Article's Alert", notes = "게시글 보면 관련된 알람 모두 지우기")
    public ResponseEntity<?> deleteArticleAlert(@PathVariable String nickname, @PathVariable String articleIdx) {
        if (alertService.deleteArticleAlert(nickname, articleIdx)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }
    }
}
