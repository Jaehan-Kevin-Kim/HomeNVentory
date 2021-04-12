package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author 841898
 */
public class InventoryService {

    public Item get(int id) throws Exception {
        ItemsDB itemsDB = new ItemsDB();
        Item item = itemsDB.get(id);
        return item;
    }

    public List<Item> getAll(String email) throws Exception {
        ItemsDB itemsDB = new ItemsDB();
        List<Item> items = itemsDB.getAll(email);
        return items;
    }

    public void insert(String itemName, double price, String owner, int category) throws Exception {
        Item item = new Item(0, itemName, price);
        UserDB userDB = new UserDB();
        User user = userDB.get(owner);
        item.setOwner(user);

        CategoriesDB categoriesDB = new CategoriesDB();
        Category categoryObj = categoriesDB.get(category);
        item.setCategory(categoryObj);

        ItemsDB itemsDB = new ItemsDB();
        itemsDB.insert(item);
    }

    public void update(int itemId, String itemName, double price, String owner, int category) throws Exception {
        ItemsDB itemsDB = new ItemsDB();
        Item item = itemsDB.get(itemId);
        item.setItemName(itemName);
        item.setPrice(price);

        CategoriesDB categoriesDB = new CategoriesDB();
        Category categoryObj = categoriesDB.get(category);
        item.setCategory(categoryObj);

        itemsDB.update(item);
    }

    public void delete(int itemId, String email) throws Exception {
        ItemsDB itemsDB = new ItemsDB();
        Item item = itemsDB.get(itemId);
        if (!item.getOwner().getEmail().equals(email)) {
            return;
        } else {
            itemsDB.delete(item);
        }
    }
}
