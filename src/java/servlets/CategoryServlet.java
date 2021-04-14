package servlets;

import dataaccess.CategoriesDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.CategoryService;

/**
 *
 * @author 841898
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        AccountService accountService = new AccountService();
        CategoriesDB categoriesDB = new CategoriesDB();

        try {
            HttpSession session = request.getSession();
            session.setAttribute("categoryPage", "categoryPage");
            session.setAttribute("adminPage", null);

            List<Category> categories = categoriesDB.getAll();
            request.setAttribute("categories", categories);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriesDB categoriesDB = new CategoriesDB();
        CategoryService categoryService = new CategoryService();
        String action = request.getParameter("action");
        String categoryId = request.getParameter("categoryId");
        Category category;
     
                    

        try {
        
            
            switch (action) {
                case "addCategory":
                    String categoryName = request.getParameter("categoryName");
                    categoryService.insert(categoryName);
                    request.setAttribute("msg", "Category added.");
                    break;
                case "editActivation":
                    category = categoryService.get(Integer.parseInt(categoryId));
                    request.setAttribute("category", category);
                    request.setAttribute("editActivation", "editActivation");
//                    request.setAttribute("user", accountService.get(email));
                    break;
                case "editCategory":
                    category = categoryService.get(Integer.parseInt(categoryId));
                    request.setAttribute("category", category);

                    String editCategoryName = request.getParameter("editCategoryName");

                    categoryService.update(category.getCategoryId(), editCategoryName);
                    request.setAttribute("msg", "Category is edited.");
                    break;
                case "cancel":
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Category> categoriesList = categoriesDB.getAll();
            request.setAttribute("categories", categoriesList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

}
