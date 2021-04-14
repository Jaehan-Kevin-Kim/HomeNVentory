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

    public void insert(String categoryName) throws Exception {
        Category category = new Category(0, categoryName);
        CategoriesDB categoriesDB = new CategoriesDB();
        categoriesDB.insert(category);
   
    }

    public void update(int categoryId, String categoryName) throws Exception {
        CategoriesDB categoriesDB = new CategoriesDB();
         Category category = categoriesDB.get(categoryId);
         category.setCategoryName(categoryName);
     
     
         categoriesDB.update(category);
        
    }

  
}
