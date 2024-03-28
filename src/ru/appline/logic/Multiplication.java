package ru.appline.logic;

import java.io.Serializable;

public class Multiplication implements Serializable {
    private double a;
    private double b;
    private String math;

    public Multiplication(double a, double b, String math) {
        this.a = a;
        this.b = b;
        this.math = math;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }
}
