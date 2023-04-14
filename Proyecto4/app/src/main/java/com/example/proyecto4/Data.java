package com.example.proyecto4;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Data {
    // Data members
    private ArrayList<String> gastos = new ArrayList<String>();
    private ArrayList<GastoNuevo> gastosUsuario = new ArrayList<GastoNuevo>();
    private ArrayList<GastoNuevo> sorting = new ArrayList<GastoNuevo>();


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

        //If a gastos category is deleted, all the gastos with that category will be deleted
        int count = 0;
        while(count < gastosUsuario.size()){
            if(gastosUsuario.get(count).getGasto().equals(gastos.get(index))){
                gastosUsuario.remove(count);
            }else{
                count++;
            }
        }
        // Delete the gastos category
        this.gastos.remove(index);

    }

    // Search a gastoUsuario with the gasto
    public ArrayList<GastoNuevo> searchGastoUsuario(String gasto){
        ArrayList<GastoNuevo> gastos = new ArrayList<GastoNuevo>();
        for (GastoNuevo gastoUsuario : this.gastosUsuario) {
            Log.d("Promt",gasto);
            Log.d("Data",gastoUsuario.getGasto());
            if (gastoUsuario.getGasto().toLowerCase().equals(gasto.toLowerCase())) {
                gastos.add(gastoUsuario);
                Log.d("ArrayList",String.valueOf(gastos.size()));
                Log.d("ArrayList",gastoUsuario.getGasto());
            }
        }
        sorting = gastos;
        return gastos;
    }

    // Search a gastoUsuario with the fecha
    public ArrayList<GastoNuevo> searchGastoUsuarioFecha(String fecha){
        ArrayList<GastoNuevo> gastos = new ArrayList<GastoNuevo>();
        for (GastoNuevo gastoUsuario : gastosUsuario) {
            if (gastoUsuario.getFecha().equals(fecha)) {
                gastos.add(gastoUsuario);
            }
        }
        sorting = gastos;
        return gastos;
    }

    // Search a gastoUsuario with the value
    public ArrayList<GastoNuevo> searchValorUsuario(Float valorInicial, Float valorFinal){
        ArrayList<GastoNuevo> gastos = new ArrayList<GastoNuevo>();
        for (GastoNuevo gastoUsuario : gastosUsuario) {
            if (gastoUsuario.getValor() >= valorInicial && gastoUsuario.getValor() <= valorFinal) {
                gastos.add(gastoUsuario);
            }
        }
        sorting = gastos;
        return gastos;
    }

    // Get the total value of the sorting
    public Float getTotal(){
        Float total = 0f;
        for (GastoNuevo gastoUsuario : sorting) {
            total += gastoUsuario.getValor();
        }
        return total;
    }

    public Float getTotal(ArrayList<GastoNuevo> sorting){
        Float total = 0f;
        for (GastoNuevo gastoUsuario : sorting) {
            total += gastoUsuario.getValor();
        }
        return total;
    }


}
