package com.ssafy.YogaMate.model.dto;

public class Comment {
    private int idx;
    private int articleIdx;
    private String content;
    private String writer;
    private String regDate;

    public Comment() { }

    public Comment(int idx, int articleIdx, String content, String writer, String regDate) {
        this.idx = idx;
        this.articleIdx = articleIdx;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getArticleIdx() {
        return articleIdx;
    }

    public void setArticleIdx(int articleIdx) {
        this.articleIdx = articleIdx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idx=" + idx +
                ", articleIdx=" + articleIdx +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
