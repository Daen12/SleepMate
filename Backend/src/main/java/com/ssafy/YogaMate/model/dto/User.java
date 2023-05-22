package com.ssafy.YogaMate.model.dto;

public class User {
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String prefer1;
    private String prefer2;
    private String prefer3;

    public User() {}

    public User(String id, String password, String name, String nickname, String prefer1, String prefer2, String prefer3) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.prefer1 = prefer1;
        this.prefer2 = prefer2;
        this.prefer3 = prefer3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPrefer1() {
        return prefer1;
    }

    public void setPrefer1(String prefer1) {
        this.prefer1 = prefer1;
    }

    public String getPrefer2() {
        return prefer2;
    }

    public void setPrefer2(String prefer2) {
        this.prefer2 = prefer2;
    }

    public String getPrefer3() {
        return prefer3;
    }

    public void setPrefer3(String prefer3) {
        this.prefer3 = prefer3;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", prefer1='" + prefer1 + '\'' +
                ", prefer2='" + prefer2 + '\'' +
                ", prefer3='" + prefer3 + '\'' +
                '}';
    }
}
