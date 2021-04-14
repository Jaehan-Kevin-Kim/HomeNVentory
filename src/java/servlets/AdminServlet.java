package servlets;

import dataaccess.RoleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.AccountService;

/**
 *
 * @author 841898
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("adminPage", "adminPage");

        AccountService accountService = new AccountService();
        try {
            List<User> users = accountService.getAll();
            request.setAttribute("users", users);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService accountService = new AccountService();

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        RoleDB roleDB = new RoleDB();
        
//        List<User> users = accountService.getAll();
//            request.setAttribute("users", users);
        

        try {
           
            switch (action) {
                case "addUser":
                    accountService.insert(email, true, firstName, lastName, password, 2);
                    request.setAttribute("msg", "User: " + email + " added.");
                    break;
                case "delete":
                    accountService.delete(email);
                    request.setAttribute("msg", "User: " + email + " deleted.");
                    break;
                case "editActivation":
                    request.setAttribute("editActivation", "editActivation");
                    request.setAttribute("user", accountService.get(email));
                    
                    
                    User user = accountService.get(email);
      
             List <Role> roles = roleDB.getAll();
            request.setAttribute("roles", roles);
            Role role = roleDB.get(user.getRole().getRoleId());
            
            request.setAttribute("role", role);
                    
                    break;
                case "editUser":
                    String editEmail = request.getParameter("editEmail");
                    String editFirstName = request.getParameter("editFirstName");
                    String editLastName = request.getParameter("editLastName");
                    String editPassword = request.getParameter("editPassword");
//                    String editRole = request.getParameter("editRole");
                    String active = request.getParameter("editActive");
                    String editRole = request.getParameter("editRole");
                    
                    boolean activeUser = false;
                    if (active != null){
                        activeUser = true;
                    }
                    User editUser = accountService.get(editEmail);
                    accountService.update(editUser.getEmail(), activeUser, editFirstName, editLastName, editPassword, Integer.parseInt(editRole));
                    request.setAttribute("msg", "User: " + editEmail + " edited.");
                    break;
                case "cancel":
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            
           
            List<User> users = accountService.getAll();
//            User user = accountService.get(email);
            request.setAttribute("users", users);
//             List <Role> roles = roleDB.getAll();
//            request.setAttribute("roles", roles);
//            Role role = roleDB.get(user.getRole().getRoleId());
//            
//            request.setAttribute("role", role);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
