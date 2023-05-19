package com.ssafy.YogaMate.model.dto;

public class Board {
    private int idx;
    private int classNum;
    private String title;
    private String content;
    private int viewCnt;
    private String date;

    public Board() { }

    public Board(int idx, int classNum, String title, String content, int viewCnt, String date) {
        this.idx = idx;
        this.classNum = classNum;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.date = date;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
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

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Board{" +
                "idx=" + idx +
                ", classNum=" + classNum +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", date='" + date + '\'' +
                '}';
    }
}
