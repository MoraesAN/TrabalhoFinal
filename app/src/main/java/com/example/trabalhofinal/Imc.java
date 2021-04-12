package com.example.trabalhofinal;

public class Imc {
    public static double calcularIMC(double p, double a){ return p/(Math.pow(a , 2));}
        // p = peso e a = altura
    public static double pesoIdeal(double a){
        return ((a - 100)*0.80)*-1;
    }

}
