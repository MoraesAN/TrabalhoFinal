package com.example.trabalhofinal;

public class Imc {
    public static double calcularIMC(double p, double a){
        // p = peso e a = altura
        return p/(Math.pow(a , 2));
    }
}
