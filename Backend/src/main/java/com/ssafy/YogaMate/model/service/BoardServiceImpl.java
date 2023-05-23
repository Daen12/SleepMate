package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dao.BoardDao;
import com.ssafy.YogaMate.model.dao.CommentDao;
import com.ssafy.YogaMate.model.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardDao boardDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public int getArticlesNum() {
        return boardDao.selectAll();
    }

    @Override
    public List<Board> getArticles(int pageNum){
        return boardDao.select10(10 * (pageNum - 1));
    }

    @Override
    public int getClassifiedArticlesNum(int classnum) {
        return boardDao.selectClassifiedAll(classnum);
    }

    @Override
    public List<Board> getClassifiedArticles(int classnum, int pageNum) {
        return boardDao.selectClassified10(classnum, 10 * (pageNum - 1));
    }

    @Override
    public boolean writeArticle(Board board) {
        return boardDao.writeArticle(board);
    }

    @Override
    public Board readArticle(int idx) {
        boardDao.updateViewCnt(idx);
        return boardDao.readArticle(idx);
    }

    @Override
    public boolean updateArticle(Board board) {
        return boardDao.updateArticle(board);
    }

    @Override
    public boolean deleteArticle(int idx) {
        commentDao.deleteCommentByArticleIdx(idx);
        return boardDao.deleteArticle(idx);
    }



}
