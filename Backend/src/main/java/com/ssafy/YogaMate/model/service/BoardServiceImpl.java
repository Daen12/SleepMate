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
    public List<Board> getAllArticles(int pageNum){
        return boardDao.selectAll(pageNum - 1);
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
