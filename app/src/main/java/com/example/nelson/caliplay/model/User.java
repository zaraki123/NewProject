package com.example.nelson.caliplay.model;

/**
 * Created by Zaraki on 21/11/2015.
 */
public class User extends ModelBase {

    private String username;
    private String sex;
    private String age;
    private int height;
    private int weight;
    private String lifestyle;
    private String sportPresent;
    private String sportPast;

    public User(String age, int height, int weight, String lifestyle, String sportPresent, String sportPast) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.sportPresent = sportPresent;
        this.sportPast = sportPast;
    }

    public User(String username, String sex) {
        this.username = username;
        this.sex = sex;
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

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
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
