package g3.servelets;

import g3.Forms.SigninFormChecker;
import g3.Model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sign-in", urlPatterns = {"/sign-in"})
public class Signin extends HttpServlet {

    private static final String VIEW = "/WEB-INF/Signin.jsp";
    private static final String VIEW_ADMIN = "/WEB-INF/Admin.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(VIEW);
            dispatcher.forward(request, response);
        } else {
            User myUser = (User) request.getSession().getAttribute("user");
            if (myUser.getId_user() == 1) {
                request.getServletContext().getRequestDispatcher(VIEW_ADMIN).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SigninFormChecker checker = new SigninFormChecker(request);
        if (!checker.checkForm()) {
            //Assurer que l'utilisateur n'est plus en session
            request.getSession().invalidate();

            request.setAttribute("errors", checker.getErrors());
            request.setAttribute("userD", checker.getBean());

            request.getServletContext()
                    .getRequestDispatcher(VIEW)
                    .forward(request, response);

        } else {

            //je met l'utilisateur en session
            request.getSession().setAttribute("user", checker.getBean());

            response.sendRedirect(request.getContextPath() + "/home");

        }
    }
}
