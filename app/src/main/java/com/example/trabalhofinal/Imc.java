package com.example.trabalhofinal;

public class Imc {
    public static double calcularIMC(double p, double a){
        // p = peso e a = altura
        return p/(Math.pow(a , 2));
    }

    public static double pesoIdeal(double a){
        return (a - 100)*0.90;
    }

}
