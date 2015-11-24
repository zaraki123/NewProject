package com.example.nelson.caliplay.model;

/**
 * Created by Zaraki on 21/11/2015.
 */

public class User extends ModelBase {

    private String username, sex, age;
    private int height, weight;
    private String lifeStyle, sportPresent, sportPast;

    public User(String username, String sex, String age, int height, int weight, String lifeStyle, String sportPresent, String sportPast) {
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifeStyle = lifeStyle;
        this.sportPresent = sportPresent;
        this.sportPast = sportPast;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public String getSportPresent() {
        return sportPresent;
    }

    public void setSportPresent(String sportPresent) {
        this.sportPresent = sportPresent;
    }

    public String getSportPast() {
        return sportPast;
    }

    public void setSportPast(String sportPast) {
        this.sportPast = sportPast;
    }
}
