
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 841898
 */
public class RegisterServlet extends HttpServlet {
        String email;
        String password; 
        String firstName; 
        String lastName; 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
        String uuid = (String) request.getParameter("uuid");
        AccountService as = new AccountService();
        
        if (uuid != null){
//            request.setAttribute("uuid", uuid);
             try {
            as.insert(email, true, firstName, lastName, password, 2);
            
            getServletContext().getRequestDispatcher("/WEB-INF/registerConfirmation.jsp").forward(request, response);
            return;
        }catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        email = request.getParameter("email");
        password = request.getParameter("password");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
        String url = request.getRequestURL().toString();
        
        AccountService as = new AccountService();
        
        try {
            as.register(email, firstName, url);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            as.insert(email, true, firstName, lastName, password, 2);
//           
//        } catch (Exception ex) {
//            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
         response.sendRedirect("registerExplanation");
        
        
      
    }

}
