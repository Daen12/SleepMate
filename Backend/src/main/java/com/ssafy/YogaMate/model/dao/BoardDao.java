package com.ssafy.YogaMate.model.dao;

import com.ssafy.YogaMate.model.dto.Board;

import java.util.List;

public interface BoardDao {
    
    // 모든 게시글 가져오기
    List<Board> selectAll();

    // 게시판 글 쓰기
    boolean writeArticle(Board board);

    // 게시판 글 상세보기 + viewCnt 1 증가
    Board readArticle(int idx);
    void updateViewCnt(int idx);

    // 게시판 글 업데이트

    boolean updateArticle(Board board);
    // 게시판 글 삭제

    boolean deleteArticle(int idx);
}
