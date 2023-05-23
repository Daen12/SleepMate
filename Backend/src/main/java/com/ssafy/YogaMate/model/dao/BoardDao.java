package com.ssafy.YogaMate.model.dao;

import com.ssafy.YogaMate.model.dto.Board;

import java.util.List;

public interface BoardDao {

    // 모든 게시글의 수
    int selectAll();

    // 게시글 중 10개 씩 가져오기
    List<Board> select10(int pageNum);

    // 게시판 글 쓰기
    boolean writeArticle(Board board);

    // 게시판 글 상세보기 + viewCnt 1 증가
    Board readArticle(int idx);

    // 게시판 글 업데이트
    void updateViewCnt(int idx);
    boolean updateArticle(Board board);

    // 게시판 글 삭제
    boolean deleteArticle(int idx);

    // 카테고리 게시글 중 10개씩 가져오기
    List<Board> selectClassified10(int classnum, int pageNum);

    // 카테고리 게시글의 수
    int selectClassifiedAll(int classnum);
}
