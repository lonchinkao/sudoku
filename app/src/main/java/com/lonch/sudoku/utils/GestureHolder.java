package com.lonch.sudoku.utils;

/**
 * Created by lonch on 2/23/2018.
 */

import android.gesture.Gesture;

public class GestureHolder {
    private Gesture gesture;
    private String gestureNaam;

    public GestureHolder(Gesture gesture, String naam){
        this.gesture = gesture;
        this.gestureNaam = naam;
    }

    public Gesture getGesture(){
        return gesture;
    }

    public void setGesture(Gesture gesture){
        this.gesture = gesture;
    }

    public String getNaam(){
        return gestureNaam;
    }

    public void setNaam(String naam){
        this.gestureNaam = naam;
    }

}