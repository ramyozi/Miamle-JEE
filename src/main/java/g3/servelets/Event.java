/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.servelets;

import g3.Forms.AddAttendanceFormChecker;
import g3.Model.User;
import g3.beans.AttendanceOrm;
import g3.beans.ContainOrm;
import g3.beans.EventOrm;
import g3.dao.DaoAttendance;
import g3.dao.DaoContain;
import g3.dao.DaoEvent;
import g3.dao.DaoFactory;
import g3.dao.DaoMeal;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author stag
 */
@WebServlet("/event")
public class Event extends HttpServlet {

    private static final String VIEW = "/WEB-INF/Event.jsp";
    private DaoAttendance daoAttendance;
    private DaoContain daoContain;
    private DaoMeal daoMeal;

    @Override
    public void init() throws ServletException {
        super.init();
        daoAttendance = DaoFactory.getAttendanceDao();
        daoContain = DaoFactory.getContainDao();
        daoMeal = DaoFactory.getMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DaoEvent daoEvent = DaoFactory.getEventDao();
            EventOrm eventOrm = new EventOrm(daoEvent.getById(id));

            if (eventOrm.getEvent() == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            request.setAttribute("eventOrm", eventOrm);

            DaoAttendance attendanceDao = DaoFactory.getAttendanceDao();
            Collection<AttendanceOrm> attendanceOrms = attendanceDao.getOrmById(id);
            request.setAttribute("attendanceOrms", attendanceOrms);

            request.getSession().setAttribute("event", eventOrm.getEvent());

            Collection<AttendanceOrm> attendees = daoAttendance.getOrmById(eventOrm.getEvent().getId());
            request.setAttribute("attendees", attendees);

            request.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        } catch (NumberFormatException nfe) {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        AddAttendanceFormChecker checker = new AddAttendanceFormChecker(request);
        if (!checker.checkForm()) {
            request.setAttribute("newAttendanceData", checker.getBean());
            request.setAttribute("errors", checker.getErrors());
            doGet(request, response);
        } else {
            int eventId = checker.getBean().getId_event();
            Collection<AttendanceOrm> attendees = daoAttendance.getOrmById(eventId);
            request.setAttribute("attendees", attendees);

            doGet(request, response);
        }
    }
}
