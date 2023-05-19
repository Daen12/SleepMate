package com.ssafy.YogaMate.controller;


import com.ssafy.YogaMate.model.dto.Board;
import com.ssafy.YogaMate.model.dto.Comment;
import com.ssafy.YogaMate.model.service.CommentService;
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
@RequestMapping("/api/comment")
@Api(tags = "댓글 컨트롤러")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{article_idx}")
    @ApiOperation(value = "Get All Comment", notes = "게시글에 해당하는 모든 댓글 가져오기")
    public ResponseEntity<Map<String, Object>> getAllComments(@PathVariable int article_idx) {
        Map<String, Object> result = new HashMap<>();
        List<Comment> commentList = commentService.getAllComments(article_idx);
        HttpStatus status = null;

        if (commentList == null && commentList.size() == 0) {
            result.put("Comment Number", 0);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("Comment Number", commentList.size());
            result.put("comments", commentList);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @PostMapping("/write")
    @ApiOperation(value = "Create Comment", notes = "댓글 작성")
    public ResponseEntity<?> writeComment(@RequestBody Comment comment) {
        if (commentService.writeComment(comment)) {
            return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Comment", notes = "댓글 수정")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        if (commentService.updateComment(comment)) {
            return new ResponseEntity<Comment>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{idx}")
    @ApiOperation(value = "Delete Comment", notes = "댓글 삭제")
    public ResponseEntity<Void> deleteComment(@PathVariable int idx) {
        if (commentService.deleteComment(idx)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
