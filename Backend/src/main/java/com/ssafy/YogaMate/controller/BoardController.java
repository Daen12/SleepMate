package com.ssafy.YogaMate.controller;

import com.ssafy.YogaMate.model.dto.Board;
import com.ssafy.YogaMate.model.service.BoardService;
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
@RequestMapping("/api/board")
@Api(tags = "게시판 컨트롤러")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Board Article", notes = "게시판 모든 글 가져오기")
    public ResponseEntity<Map<String, Object>> getAllArticles() {
        Map<String, Object> result = new HashMap<>();
        List<Board> boardList = boardService.getAllArticles();
        HttpStatus status = null;

        if (boardList == null && boardList.size() == 0) {
            result.put("Article Number", 0);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("Article Number", boardList.size());
            result.put("articles", boardList);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @PostMapping("/write")
    @ApiOperation(value = "Create Article", notes = "게시판에 글 쓰기")
    public ResponseEntity<?> writeArticle(@RequestBody Board board) {
        if (boardService.writeArticle(board)) {
            return new ResponseEntity<Board>(board, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{idx}")
    @ApiOperation(value = "Read Article", notes = "게시판 글 상세보기")
    public ResponseEntity<?> readArticle(@PathVariable int idx) {
        Board board = boardService.readArticle(idx);
        if (board != null) {
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Article", notes = "게시판 글 업데이트")
    public ResponseEntity<?> updateArticle(@RequestBody Board board) {
        if (boardService.updateArticle(board)) {
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{idx}")
    @ApiOperation(value = "Delete Article", notes = "게시판 글 삭제")
    public ResponseEntity<Void> deleteArticle(@PathVariable int idx) {
        if (boardService.deleteArticle(idx)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
