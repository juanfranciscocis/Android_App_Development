package com.example.proyecto2;

import javafx.concurrent.Task;

import java.math.BigInteger;

public class Factorial extends Task<BigInteger> {

    private BigInteger inicio;
    private BigInteger fin;

    public BigInteger subTotal;

    public long tiempo;




    public Factorial(BigInteger inicio, BigInteger fin) {
        this.inicio = inicio;
        this.fin = fin;
    }



    BigInteger factorial(int beg, int end) {
        long startTime = System.currentTimeMillis();
        // comprobar si hay entrada inválida
        if (beg < 0 || end < 0 || beg > end) {
            throw new IllegalArgumentException("Invalid input values");
        }

        BigInteger fact = BigInteger.valueOf(1);

        for (int i = beg; i <= end; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        long endTime = System.currentTimeMillis();

        this.tiempo = (endTime - startTime);

        return fact;
    }




    @Override
    protected BigInteger call() throws Exception {
        BigInteger f = factorial(inicio.intValue(), fin.intValue());
        subTotal = f;
        return f;
    }
}
