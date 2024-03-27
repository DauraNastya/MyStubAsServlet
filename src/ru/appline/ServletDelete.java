package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        int id = jsonObject.get("id").getAsInt();

        response.setContentType("application/json; charset-utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        if (id > 0) {
            if (id < model.getFromList().size()) {
                model.getFromList().remove(id);
                printWriter.print(gson.toJson("Пользователь успешно удалён!"));
            } else {
                printWriter.print(gson.toJson("Нет пользователя с таким ID! :("));
            }
        } else {
            printWriter.print(gson.toJson("ID должен быть строго больше нуля! :("));
        }
    }
}