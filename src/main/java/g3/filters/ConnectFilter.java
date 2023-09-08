/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.filters;

import g3.Model.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stag
 */
public class ConnectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        // Travail avant l'appel à la servlet
        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).getId() == 1) {
            session.setAttribute("isAdmin", true);
        }
        // Appel à la servlet
        chain.doFilter(request, response);
        // Travail après la servlet mais avant la réponse

    }

    @Override
    public void destroy() {
    }

}
