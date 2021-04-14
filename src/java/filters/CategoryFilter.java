/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author 841898
 */
public class CategoryFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String email = (String) session.getAttribute("email");
        String inventoryPage = (String) session.getAttribute("inventoryPage");
        String accountPage = (String) session.getAttribute("accountPage");
        UserDB userDB = new UserDB();
        User user;
        try {
            user = userDB.get(email);
            int roleId = user.getRole().getRoleId();
            
            if ((roleId == 1 || roleId == 3)) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                if (inventoryPage != null) {
                    httpResponse.sendRedirect("inventory");
                    return;
                } else if (accountPage != null) {
                    httpResponse.sendRedirect("account");
                    return;
//                httpResponse.sendRedirect("inventory");
//                return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
    
}
