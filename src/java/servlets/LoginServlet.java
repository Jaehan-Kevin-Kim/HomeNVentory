package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 841898
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logout = request.getParameter("logout");
        String inventoryPage = (String) session.getAttribute("inventoryPage");
        String adminPage = (String) session.getAttribute("adminPage");
        String accountPage = (String) session.getAttribute("accountPage");
        String email = (String) session.getAttribute("email");
        
        AccountService accountService = new AccountService();
        try {
            User user = accountService.get(email);
            if (user.getActive() == false){
                request.setAttribute("msg", "Your account is not active. Please contact to admin to reactive your status");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (logout != null) {
            session.invalidate();
            session = request.getSession();
            request.setAttribute("msg", "You have successully logged out.");
        } else if (adminPage != null) {
            response.sendRedirect("admin");
            return;
        } else if (inventoryPage != null) {
            response.sendRedirect("inventory");
            return;
        } else if (accountPage != null){
            response.sendRedirect("account");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountService accountService = new AccountService();
        User user = accountService.login(email, password);

        if (user == null) {
            if (email == null || email.equals("")) {
                request.setAttribute("msg", "Please type the user Information");

            } else {
                request.setAttribute("msg", "Invalid User Information");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        if (user.getRole().getRoleId() == 2) {
            response.sendRedirect("inventory");
        } else {
            response.sendRedirect("admin");
        }

    }

}
