/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g3.servelets;

import g3.Model.Attendance;
import g3.beans.EventOrm;
import g3.dao.DaoEvent;
import g3.dao.DaoFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet(name = "MyEvents", urlPatterns = {"/myEvents"})
public class MyEvents extends HttpServlet {

    private static final String VIEW = "/WEB-INF/MyEvents.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //verifier que  user est connecté 
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher(VIEW)
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                DaoEvent daoEvent = DaoFactory.getEventDao();
                EventOrm eventOrm = new EventOrm(daoEvent.getById(id));
                request.setAttribute("eventOrm", eventOrm);

                Collection<Attendance> ListAttendance = DaoFactory.getAttendanceDao().GetTotalAttendanceByUserId(id);
                request.setAttribute("myEvents", ListAttendance);
                request.getRequestDispatcher(VIEW)
                        .forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MyEvents.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getContextPath() + "/home");

        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

}
