package com.example.nelson.caliplay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class Exercise extends ModelBase implements Parcelable {

    private String name, typeOfContraction, typeOfMovement, videoId;
    private int seconds, level, sublevel, reps, kg, sets, frequency, tonnage, volume;
    private int completed;

    public Exercise(String name, String videoId, String typeOfContraction, String typeOfMovement, int seconds, int reps,
                    int kg, int sets, int volume, int frequency, int tonnage, int level, int completed) {
        this.name = name;
        this.videoId = videoId;
        this.typeOfContraction = typeOfContraction;
        this.typeOfMovement = typeOfMovement;
        this.seconds = seconds;
        this.reps = reps;
        this.kg = kg;
        this.sets = sets;
        this.frequency = frequency;
        this.tonnage = tonnage;
        this.level = level;
        this.volume = volume;
        this.completed = completed;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getTonnage() {
        return tonnage;
    }

    public void setTonnage(int tonnage) {
        this.tonnage = tonnage;
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

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getSublevel() {
        return sublevel;
    }

    public void setSublevel(int sublevel) {
        this.sublevel = sublevel;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(typeOfContraction);
        dest.writeString(typeOfMovement);
        dest.writeString(videoId);
        dest.writeInt(seconds);
        dest.writeInt(reps);
        dest.writeInt(level);
        dest.writeInt(completed);

    }

    public static final Parcelable.Creator<Exercise> CREATOR
            = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }

    };

    public Exercise(Parcel in) {
        name = in.readString();
        typeOfContraction = in.readString();
        typeOfMovement = in.readString();
        videoId = in.readString();
        seconds = in.readInt();
        reps = in.readInt();
        level = in.readInt();
        completed = in.readInt();
    }
}
