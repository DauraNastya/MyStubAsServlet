package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Calculator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculate")
public class ServletCalculate extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jsonBuffer = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(jsonBuffer), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        double a = jsonObject.get("a").getAsDouble();
        double b = jsonObject.get("b").getAsDouble();
        String math = jsonObject.get("math").getAsString();

        response.setContentType("application/json; charset-utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();

        Calculator calculator;
        switch (math) {
            case "+":
                calculator = new Calculator(a, b, math);
                calculator.add();
                printWriter.print(gson.toJson(calculator.getResult()));
                break;
            case "-":
                calculator = new Calculator(a, b, math);
                calculator.subtrack();
                printWriter.print(gson.toJson(calculator.getResult()));
                break;
            case "*":
                calculator = new Calculator(a, b, math);
                calculator.multiply();
                printWriter.print(gson.toJson(calculator.getResult()));
                break;
            case "/":
                if (b == 0) {
                    printWriter.print(gson.toJson("Делить на нуль нельзя!"));
                    break;
                }
                calculator = new Calculator(a, b, math);
                calculator.divide();
                printWriter.print(gson.toJson(calculator.getResult()));
                break;
            default:
                printWriter.print(gson.toJson("Вы выбрали неизвестное действие! Возможны: + - * /"));
                break;
        }
    }
}