package com.example.nelson.caliplay.model;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class Exercise extends ModelBase {

    private String name, typeOfContraction, typeOfMovement, videoId;
    private int seconds, level, sublevel;

    public Exercise(String name, String videoId, String typeOfContraction, String typeOfMovement, int seconds, int level, int sublevel) {
        this.name = name;
        this.videoId = videoId;
        this.typeOfContraction = typeOfContraction;
        this.typeOfMovement = typeOfMovement;
        this.seconds = seconds;
        this.level = level;
        this.sublevel = sublevel;
    }



    public String getExerciseName() {
        return name;
    }

    public void setExerciseName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTypeOfContraction() {
        return typeOfContraction;
    }

    public void setTypeOfContraction(String typeOfContraction) {
        this.typeOfContraction = typeOfContraction;
    }

    public String getTypeOfMovement() {
        return typeOfMovement;
    }

    public void setTypeOfMovement(String typeOfMovement) {
        this.typeOfMovement = typeOfMovement;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSublevel() {
        return sublevel;
    }

    public void setSublevel(int sublevel) {
        this.sublevel = sublevel;
    }
}
