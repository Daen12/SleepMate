package com.ssafy.YogaMate.model.dto;

public class AlertDto {
    private int articleIdx;
    private String title;
    private String content;
    private String writer;
    private String regdate;

    public AlertDto() {
    }

    public AlertDto(int articleIdx, String title, String content, String writer, String regdate) {
        this.articleIdx = articleIdx;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regdate = regdate;
    }

    public int getArticleIdx() {
        return articleIdx;
    }

    public void setArticleIdx(int articleIdx) {
        this.articleIdx = articleIdx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "AlertDto{" +
                "articleIdx=" + articleIdx +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
