package com.treehugers.gifted.treehuggers;

import java.io.Serializable;

public class Tree implements Serializable {

    public double longtitude;
    public double latitude;

    public Tree(double longtitude, double latitude){
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
