package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dto.Board;

import java.util.List;

public interface BoardService {

    // 게시판의 모든 글 가져오기 (기본 값 : 날짜 순)
    List<Board> getAllArticles();

    // 게시판에 글 쓰기
    boolean writeArticle(Board board);

    // 게시판 글 상세보기
    Board readArticle(int idx);

    // 게시판 글 업데이트
    boolean updateArticle(Board board);

    // 게시판 글 삭제

    boolean deleteArticle(int idx);
}
