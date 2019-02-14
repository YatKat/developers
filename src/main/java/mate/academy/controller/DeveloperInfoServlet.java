package mate.academy.controller;
import mate.academy.model.Developer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class DeveloperInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //set response content type and encoding
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Developer developer = new Developer("Bon", 34, Developer.Sex.FEMALE, new BigDecimal(1100));
        req.setAttribute("developer", developer);
        req.getRequestDispatcher("/views/info.jsp").forward(req, resp);
    }
}
