package com.ssafy.YogaMate.model.service;

import com.ssafy.YogaMate.model.dao.CommentDao;
import com.ssafy.YogaMate.model.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getAllComments(int articleIdx) {
        return commentDao.getAllComments(articleIdx);
    }

    @Override
    public boolean writeComment(Comment comment) {
        return commentDao.writeComment(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public boolean deleteComment(int idx) {
        return commentDao.deleteComment(idx);
    }
}
