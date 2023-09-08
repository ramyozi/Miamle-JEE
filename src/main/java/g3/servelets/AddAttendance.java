/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.servelets;

import g3.Forms.AddAttendanceFormChecker;
import g3.Model.User;
import g3.dao.DaoFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stag
 */
@WebServlet(name = "AddAttendance", urlPatterns = {"/addAttendance"})
public class AddAttendance extends HttpServlet {

    private static final String VIEW = "/WEB-INF/Event.jsp";

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
        // Récupération de l'utilisateur connecté
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        AddAttendanceFormChecker checker = new AddAttendanceFormChecker(request);
        if (!checker.checkForm()) {
            request.setAttribute("newAttendanceData", checker.getBean());
            request.setAttribute("errors", checker.getErrors());
            request.getServletContext()
                    .getRequestDispatcher(VIEW)
                    .forward(request, response);
        } else {
            // Reload the Event page
            response.sendRedirect(request.getContextPath() + "/event?id=" + checker.getBean().getId_event());
        }
    }
}
