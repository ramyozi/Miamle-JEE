/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.servelets;

import g3.Model.Event;
import g3.dao.DaoEvent;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet(name = "editEvent", urlPatterns = {"/editEvent"})
public class EditEvent extends HttpServlet {

    private static final String VIEW = "/WEB-INF/EditEvent.jsp";
    DaoEvent dao = new DaoEvent();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int eventId = Integer.parseInt(request.getParameter("id"));
        Event event = dao.getById(eventId);

        request.setAttribute("event", event);

        request.getRequestDispatcher(VIEW)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the event to update from the request parameters
        int eventId = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);
        String description = request.getParameter("description");

        Event event = dao.getById(eventId);

        event.setName(name);
        event.setDate_event(date);
        event.setDescription(description);
        dao.update(event);

        response.sendRedirect(request.getContextPath() + "/home");
    }

}
