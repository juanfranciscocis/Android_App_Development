package com.example.proyecto4;

public class GastoNuevo {
    private String gasto;
    private String fecha;
    private Float valor;

    public GastoNuevo(String gasto, String fecha, Float valor) {
        this.gasto = gasto;
        this.fecha = fecha;
        this.valor = valor;
    }

    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
