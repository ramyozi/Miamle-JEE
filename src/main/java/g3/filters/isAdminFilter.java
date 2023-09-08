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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stag
 */
@WebFilter(urlPatterns = "/*")
public class isAdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // À l'aller : je travaille sur la requête
        HttpSession session = ((HttpServletRequest) request).getSession();

        User user = (User) session.getAttribute("user");
        if (user != null && user.getId() == 1) {
            session.setAttribute("isAdmin", true);
        } else {
            session.removeAttribute("isAdmin");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
