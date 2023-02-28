package com.example.proyecto2;

import java.util.ArrayList;

public class BooleanArrayListener {
    private ArrayList<Boolean> array;
    private BooleanArrayListenerInterface listener;

    public BooleanArrayListener(ArrayList<Boolean> completado, BooleanArrayListenerInterface listener) {
        this.array = array;
        this.listener = listener;
    }


    public void arrayChanged(ArrayList<Boolean> array) {
        boolean allTrue = true;
        for (boolean b : array) {
            if (!b) {
                allTrue = false;
                break;
            }
        }
        if (allTrue) {
            listener.mostrarResultados();
        }
    }

}