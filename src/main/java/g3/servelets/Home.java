package g3.servelets;

import g3.dao.DaoFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    private static final String VIEW = "/WEB-INF/Home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // On récupère les articles depuis la DB
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException nfe) {
            page = 1;
        }

        int offset = (page - 1) * 10;
        request.setAttribute("allEvents", DaoFactory.getEventDao().getBatchFrom(10, offset));
        request.setAttribute("page", page);
        request.setAttribute("maxPage", 1 + (DaoFactory.getEventDao().count() - 1) / 10);

// les tests
//        Collection<Event> allEvents = DaoFactory.getEventDao().getAll();
//        request.setAttribute("allEvents", allEvents);
        
        // On appelle la vue
        request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Home.jsp")
                .forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
