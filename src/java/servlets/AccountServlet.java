package servlets;

import java.io.IOException;
import java.util.List;
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
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService accountService = new AccountService();
        try {
            HttpSession session = request.getSession();
            session.setAttribute("accountPage", "accountPage");
            session.setAttribute("inventoryPage", null);
            String email = (String) session.getAttribute("email");
            User user = accountService.get(email);
            request.setAttribute("user", user);
            
//             if (user.getActive() == false){
//                 response.sendRedirect("login");
//                 return;
//             }
               

        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            AccountService accountService = new AccountService();

            String email = request.getParameter("editEmail");
            String firstName = request.getParameter("editFirstName");
            String lastName = request.getParameter("editLastName");
            String password = request.getParameter("editPassword");
            String active = request.getParameter("editActive");
            String action = request.getParameter("action");
            User user = accountService.get(email);
  
            boolean activeUser = true;

            try {
                if (active == null) {
                    activeUser = false;
                } 
     

                switch (action) {
                    case "editAccount":
                        accountService.update(email, activeUser, firstName, lastName, password, 2);
                        request.setAttribute("msg", "The account detail edited.");
                        break;
                    case "cancel":
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                user = accountService.get(email);

                request.setAttribute("user", user);

            } catch (Exception e) {
                e.printStackTrace();
            }

            getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
