package com.ssafy.YogaMate.model.dto;

public class Board {
    private int idx;
    private int classnum;
    private String title;
    private String content;
    private String writer;
    private int viewcnt;
    private String regdate;

    public Board() {
    }

    public Board(int idx, int classnum, String title, String content, String writer, int viewcnt, String regdate) {
        this.idx = idx;
        this.classnum = classnum;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewcnt = viewcnt;
        this.regdate = regdate;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getClassnum() {
        return classnum;
    }

    public void setClassnum(int classnum) {
        this.classnum = classnum;
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

    public int getViewcnt() {
        return viewcnt;
    }

    public void setViewcnt(int viewcnt) {
        this.viewcnt = viewcnt;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "idx=" + idx +
                ", classnum=" + classnum +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", viewcnt=" + viewcnt +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
