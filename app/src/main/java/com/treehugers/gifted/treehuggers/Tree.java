package com.treehugers.gifted.treehuggers;

import java.io.Serializable;

public class Tree implements Serializable {

    public double longtitude;
    public double latitude;
    public boolean hugged;

    public Tree(double longtitude, double latitude, boolean hugged){
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.hugged = hugged;
    }
}
