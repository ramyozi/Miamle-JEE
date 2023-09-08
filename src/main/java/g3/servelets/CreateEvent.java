/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.servelets;

import g3.Forms.CreateEventFormChecker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateEvent", urlPatterns = {"/createEvent"})
public class CreateEvent extends HttpServlet {

    private static final String VIEW = "/WEB-INF/CreateEvent.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CreateEventFormChecker checker = new CreateEventFormChecker(request);
        if (!checker.checkForm()) {
            request.setAttribute("event", checker.getBean());
            request.setAttribute("errors", checker.getErrors());
            request.getServletContext()
                    .getRequestDispatcher(VIEW)
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }

    }
}
