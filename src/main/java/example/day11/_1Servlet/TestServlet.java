package example.day11._1Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
//        // 요청 객체 : HttpServletRequest
//        String id = req.getParameter("id");
//        System.out.println("id = " + id);
//
//        int type = Integer.parseInt(req.getParameter("type"));
//        System.out.println("type = " + type);
//
//        // 응답 객체 : HttpServletResponse
//        // resp.setContentType("text/html");
//        resp.setContentType("application/json");
//        resp.getWriter().println("\"msg\" : \"메시지\"");

    }
}
