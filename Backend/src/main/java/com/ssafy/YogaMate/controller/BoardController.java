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
    @ApiOperation(value = "Get Board Articles Number", notes = "게시판 글의 총 개수 가져오기")
    public ResponseEntity<Integer> getArticlesNum() {
        int articleNum = boardService.getArticlesNum();
        return new ResponseEntity<Integer>(articleNum, HttpStatus.OK);
    }

    @GetMapping("/{pageNum}")
    @ApiOperation(value = "Get 10 Board Articles", notes = "게시판 모든 글 중 10개 가져오기")
    public ResponseEntity<Map<String, Object>> getArticles(@PathVariable int pageNum) {
        Map<String, Object> result = new HashMap<>();
        List<Board> boardList = boardService.getArticles(pageNum);
        HttpStatus status = null;

        if (boardList == null && boardList.size() == 0) {
            result.put("Article Number", 0);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("articles", boardList);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @GetMapping("/class/{classnum}")
    @ApiOperation(value = "Get Classified Board Articles Number", notes = "특정 카테고리에 해당하는 글의 총 개수 가져오기")
    public ResponseEntity<Integer> getClassifiedArticlesNum(@PathVariable int classnum) {
        int classifiedArticleNum = boardService.getClassifiedArticlesNum(classnum);
        return new ResponseEntity<Integer>(classifiedArticleNum, HttpStatus.OK);
    }

    @GetMapping("/class/{classnum}/{pageNum}")
    @ApiOperation(value = "Get 10 Classified Board Articles", notes = "게시판 모든 카테고리에 해당하는 글 중 10개 가져오기")
    public ResponseEntity<Map<String, Object>> getClassifiedArticles(@PathVariable int classnum,  @PathVariable int pageNum) {
        Map<String, Object> result = new HashMap<>();
        List<Board> boardList = boardService.getClassifiedArticles(classnum, pageNum);
        HttpStatus status = null;

        if (boardList == null && boardList.size() == 0) {
            result.put("Article Number", 0);
            status = HttpStatus.ACCEPTED;
        } else {
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
