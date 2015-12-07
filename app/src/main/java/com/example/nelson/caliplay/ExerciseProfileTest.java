package com.example.nelson.caliplay;

import android.os.Parcel;

import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class ExerciseProfileTest {

    private Exercise tuckHollow1 = new Exercise("TuckHollow1", "6i1LsA5NDMQ", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 1, 0);
    private Exercise tuckHollow2 = new Exercise("TuckHollow2", "l6s1F0Xj0wk", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 2, 0);
    private Exercise tuckHollow3 = new Exercise("TuckHollow3", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 3, 0);
    private Exercise hollow1 = new Exercise("Hollow1", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 4, 0);
    private Exercise hollow2 = new Exercise("Hollow2", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 5, 0);
    private Exercise hollow3 = new Exercise("Hollow3", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.CORE.toString(), 0, 6, 0);
    private Exercise hollowPlank1 = new Exercise("HollowPlank1", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.PUSHING.toString(), 0, 1, 0);
    private Exercise hollowPlank2 = new Exercise("HolloowPlank2", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.PUSHING.toString(), 0, 1, 0);
    private Exercise hollowPlank3 = new Exercise("HollowPlank3", "", TypeOfContraction.ISOMETRIC.toString(), TypeOfMovement.PUSHING.toString(), 0, 1, 0);
    private Exercise rowPullup1 = new Exercise("RowPullup1", "", TypeOfContraction.DYNAMIC.toString(), TypeOfMovement.PULLING.toString(), 0, 1, 0);
    private Exercise rowPullup2 = new Exercise("RowPullup2", "", TypeOfContraction.DYNAMIC.toString(), TypeOfMovement.PULLING.toString(), 0, 1, 0);
    private Exercise rowPullup3 = new Exercise("RowPullup3", "", TypeOfContraction.DYNAMIC.toString(), TypeOfMovement.PULLING.toString(), 0, 1, 0);

    private enum TypeOfContraction {
        DYNAMIC("Dynamic"),
        ISOMETRIC("Isometric");
        private final String name;

        private TypeOfContraction(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    private enum TypeOfMovement {
        PUSHING("Pushing"),
        PULLING("Pulling"),
        CORE("Core");
        private final String name;

        private TypeOfMovement(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }

    }


    public Exercise getTuckHollow1() {
        return tuckHollow1;
    }

    public void setTuckHollow1(Exercise tuckHollow1) {
        this.tuckHollow1 = tuckHollow1;
    }

    public Exercise getTuckHollow2() {
        return tuckHollow2;
    }

    public void setTuckHollow2(Exercise tuckHollow2) {
        this.tuckHollow2 = tuckHollow2;
    }

    public Exercise getTuckHollow3() {
        return tuckHollow3;
    }

    public void setTuckHollow3(Exercise tuckHollow3) {
        this.tuckHollow3 = tuckHollow3;
    }

    public Exercise getHollow1() {
        return hollow1;
    }

    public void setHollow1(Exercise hollow1) {
        this.hollow1 = hollow1;
    }

    public Exercise getHollow2() {
        return hollow2;
    }

    public void setHollow2(Exercise hollow2) {
        this.hollow2 = hollow2;
    }

    public Exercise getHollow3() {
        return hollow3;
    }

    public void setHollow3(Exercise hollow3) {
        this.hollow3 = hollow3;
    }

    public Exercise getHollowPlank1() {
        return hollowPlank1;
    }

    public void setHollowPlank1(Exercise hollowPlank1) {
        this.hollowPlank1 = hollowPlank1;
    }

    public Exercise getHollowPlank2() {
        return hollowPlank2;
    }

    public void setHollowPlank2(Exercise hollowPlank2) {
        this.hollowPlank2 = hollowPlank2;
    }

    public Exercise getHollowPlank3() {
        return hollowPlank3;
    }

    public void setHollowPlank3(Exercise hollowPlank3) {
        this.hollowPlank3 = hollowPlank3;
    }

    public Exercise getRowPullup1() {
        return rowPullup1;
    }

    public void setRowPullup1(Exercise rowPullup1) {
        this.rowPullup1 = rowPullup1;
    }

    public Exercise getRowPullup2() {
        return rowPullup2;
    }

    public void setRowPullup2(Exercise rowPullup2) {
        this.rowPullup2 = rowPullup2;
    }

    public Exercise getRowPullup3() {
        return rowPullup3;
    }

    public void setRowPullup3(Exercise rowPullup3) {
        this.rowPullup3 = rowPullup3;
    }
}
