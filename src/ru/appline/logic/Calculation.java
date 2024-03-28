package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Calculation implements Serializable {

    Map<String, Double> result;
    Multiplication multiplication;

    public Calculation(double a, double b, String math) {
        multiplication = new Multiplication(a, b, math);
        result = new HashMap<>();
    }

    public void multiply() {
        result.put("result", multiplication.getA() * multiplication.getB());
    }

    public Map<String, Double> getResult() {
        return result;
    }
}
