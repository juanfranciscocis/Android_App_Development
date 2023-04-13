package com.example.proyecto4;

import android.util.Log;

import java.util.ArrayList;

public class Data {
    // Data members
    private ArrayList<String> gastos = new ArrayList<String>();
    private ArrayList<GastoNuevo> gastosUsuario = new ArrayList<GastoNuevo>();


    // Singleton
    private static Data instance = new Data();
    // Get instance
    public static Data getInstance(){
        return instance;
    }

    // Constructor
    // Will add some default values to the gastos list
    private Data(){
        gastos.add("Alimentacion");
        gastos.add("Transporte");
        gastos.add("Entretenimiento");
        gastos.add("Salud");
        gastos.add("Educacion");
    }

    // Methods
    // Getters and setters
    public ArrayList<String> getGastos() {
        return gastos;
    }
    public void setGastos(String gastos) {
        this.gastos.add(gastos);
    }

    public ArrayList<GastoNuevo> getGastosUsuario() {
        return gastosUsuario;
    }
    public void setGastosUsuario(GastoNuevo gastosUsuario) {
        this.gastosUsuario.add(gastosUsuario);
        Log.d("Data",String.valueOf(this.gastosUsuario.size()));
    }


    // Delete a gastos with the index
    public void deleteGastos(int index){
        this.gastos.remove(index);
    }

    // Search a gastoUsuario with the gasto
    public GastoNuevo searchGastoUsuario(String gasto){
        for (GastoNuevo gastoUsuario : gastosUsuario) {
            if (gastoUsuario.getGasto().equals(gasto)) {
                return gastoUsuario;
            }
        }
        return null;
    }

    // Search a gastoUsuario with the fecha
    public GastoNuevo searchGastoUsuarioFecha(String fecha){
        for (GastoNuevo gastoUsuario : gastosUsuario) {
            if (gastoUsuario.getFecha().equals(fecha)) {
                return gastoUsuario;
            }
        }
        return null;
    }

    // Search a gastoUsuario with the value
    public GastoNuevo searchGastoUsuario(Float valor){
        for (GastoNuevo gastoUsuario : gastosUsuario) {
            if (gastoUsuario.getValor().equals(valor)) {
                return gastoUsuario;
            }
        }
        return null;
    }






}
