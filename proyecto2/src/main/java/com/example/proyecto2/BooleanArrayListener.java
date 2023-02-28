package com.example.proyecto2;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class BooleanArrayListener extends Task<Void> {
    private ArrayList<Boolean> array;
    private BooleanArrayListenerInterface listener;
    private boolean allTrue;

    public BooleanArrayListener(ArrayList<Boolean> completado, BooleanArrayListenerInterface listener) {
        this.array = array;
        this.listener = listener;
        allTrue = false;
    }


    public void arrayChanged(ArrayList<Boolean> array) {
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


    @Override
    protected Void call() throws Exception {
        System.out.println("Starting listener");
        while (allTrue==false) {
            System.out.println("Waiting for all tasks to complete");
            arrayChanged(this.array);
        }
        System.out.println("All tasks completed");
        return null;
    }
}