/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import models.Category;

/**
 *
 * @author 841898
 */
public class CategoryService {
     public Category get(int categoryId) throws Exception {
         CategoriesDB categoriesDB = new CategoriesDB();
         Category category = categoriesDB.get(categoryId);
         return category;
         
    }

//    public void insert(String itemName, double price, String owner, int category) throws Exception {
//        Item item = new Item(0, itemName, price);
//        UserDB userDB = new UserDB();
//        User user = userDB.get(owner);
//        item.setOwner(user);
//
//        CategoriesDB categoriesDB = new CategoriesDB();
//        Category categoryObj = categoriesDB.get(category);
//        item.setCategory(categoryObj);
//
//        ItemsDB itemsDB = new ItemsDB();
//        itemsDB.insert(item);
//    }

    public void update(int categoryId, String categoryName) throws Exception {
        CategoriesDB categoriesDB = new CategoriesDB();
         Category category = categoriesDB.get(categoryId);
         category.setCategoryName(categoryName);
     
     
         categoriesDB.update(category);
        
    }

  
}
