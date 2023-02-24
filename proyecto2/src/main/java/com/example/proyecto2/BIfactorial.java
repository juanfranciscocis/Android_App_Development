package com.example.proyecto2;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;


public class BIfactorial {
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String bfac = factorial(300_000).toString();
		 System.out.println(bfac.length() + ":  Tiempo: " + (System.currentTimeMillis() - startTime)+"\n"+ bfac);
		 
    }

    public static BigInteger factorial(int N)
    {
        BigInteger f = new BigInteger("1");

        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));

        return f;
    }
}