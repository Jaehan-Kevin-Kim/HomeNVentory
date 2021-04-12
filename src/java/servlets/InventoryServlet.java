package servlets;

import dataaccess.CategoriesDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;

/**
 *
 * @author 841898
 */
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InventoryService inventoryService = new InventoryService();
        AccountService accountService = new AccountService();
        CategoriesDB categoriesDB = new CategoriesDB();

        try {
            HttpSession session = request.getSession();
            session.setAttribute("inventoryPage", "inventoryPage");
            String email = (String) session.getAttribute("email");
            User user = accountService.get(email);
            List<Item> items = inventoryService.getAll(email);
            List<Category> categories = categoriesDB.getAll();
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            request.setAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InventoryService inventoryService = new InventoryService();
        AccountService accountService = new AccountService();
        CategoriesDB categoriesDB = new CategoriesDB();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        String category = request.getParameter("categories");

        String itemId = request.getParameter("itemId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "addItem":
                    int categoryId = 0;

                    try {
                        switch (category) {
                            case "kitchen":
                                categoryId = 1;
                                break;
                            case "bathroom":
                                categoryId = 2;
                                break;
                            case "living room":
                                categoryId = 3;
                                break;
                            case "basement":
                                categoryId = 4;
                                break;
                            case "bedrooom":
                                categoryId = 5;
                                break;
                            case "garage":
                                categoryId = 6;
                                break;
                            case "office":
                                categoryId = 7;
                                break;
                            case "utility room":
                                categoryId = 8;
                                break;
                            case "storage":
                                categoryId = 9;
                                break;
                            case "other":
                                categoryId = 10;
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    inventoryService.insert(name, Double.parseDouble(price), email, categoryId);
                    request.setAttribute("msg", "Item added.");
                    break;
                case "delete":
                    inventoryService.delete(Integer.parseInt(itemId), email);
                    request.setAttribute("msg", "Item deleted.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            User user = accountService.get(email);
            List<Item> items = inventoryService.getAll(email);
            List<Category> categories = categoriesDB.getAll();
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            request.setAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

    }

}
