package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Calculator implements Serializable {

    Map<String, Double> result;
    Calculation calculation;

    public Calculator(double a, double b, String math) {
        calculation = new Calculation(a, b, math);
        result = new HashMap<>();
    }

    public void add() {
        result.put("result", calculation.getA() + calculation.getB());
    }

    public void subtrack() {
        result.put("result", calculation.getA() - calculation.getB());
    }

    public void multiply() {
        result.put("result", calculation.getA() * calculation.getB());
    }

    public void divide() {
        result.put("result", calculation.getA() / calculation.getB());
    }

    public Map<String, Double> getResult() {
        return result;
    }
}