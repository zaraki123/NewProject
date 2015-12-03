package com.example.nelson.caliplay.data;

import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.model.User;

import java.util.List;

/**
 * Created by Zaraki on 21/11/2015.
 */
public interface DataManager {

    public User getUser(long userId);
    public List<User> getUserHeaders();
    public User findUser(String name);
    public long saveUser(User user);
    public boolean deleteUser(long userId);

    public Exercise getExercise(long userId);
    public List<Exercise> getExerciseHeaders();
    public Exercise findExercise(String name);
    public long saveExercise(Exercise user);
    public boolean deleteExercise(long userId);

}
