package com.example.nelson.caliplay;

import android.support.v7.app.AppCompatActivity;

import com.example.nelson.caliplay.model.Exercise;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class ExerciseProfile extends AppCompatActivity {

    private enum TypeOfContraction {
        DYNAMIC ("Dynamic"),
        ISOMETRIC ("Isometric");
        private final String name;
        private  TypeOfContraction(String s) {
            name = s;
        }

        @Override
        public String toString(){
            return name;
        }

    }

    private enum TypeOfMovement {
        PUSHING ("Pushing"),
        PULLING ("Pulling"),
        CORE ("Core");
        private final String name;
        private  TypeOfMovement(String s) {
            name = s;
        }

        @Override
        public String toString(){
            return name;
        }

    }

    private Exercise tuckHollow1 = new Exercise ("TuckHollow", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 1, 1);
    private Exercise tuckHollow2 = new Exercise ("TuckHollow", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 1, 2);
    private Exercise tuckHollow3 = new Exercise ("TuckHollow", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 1, 3);

    public ExerciseProfile(Exercise tuckHollow) {
        this.tuckHollow1 = tuckHollow;
    }

    public Exercise getTuckHollow1() {
        return tuckHollow1;
    }
}
