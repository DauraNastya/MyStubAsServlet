package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        if (id == 0) {
            printWriter.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (model.getFromList().containsKey(id)) {
                printWriter.print(gson.toJson(model.getFromList().get(id)));
            } else {
                printWriter.print(gson.toJson("Такого пользователя нет :("));
            }
        } else {
            printWriter.print(gson.toJson("ID должен быть больше нуля!"));
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter printWriter = response.getWriter();
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if (id == 0) {
//            printWriter.print("<html>" +
//                    "<h3>Доступные пользователи:</h3><br/>" +
//                    "ID пользователя: " +
//                    "<ul>");
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//                printWriter.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            printWriter.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        } else if (id > 0) {
//            if (id > model.getFromList().size()) {
//                printWriter.print("<html>" +
//                        "<h3>Такого пользователя нет :(</h3><br/>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            } else {
//                printWriter.print("<html>" +
//                        "<h3>Запрошенный пользователь:</h3><br/>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }
//        } else {
//            printWriter.print("<html>" +
//                    "<h3>ID должен быть больше нуля!</h3><br/>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
//    }
}
