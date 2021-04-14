package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;

/**
 *
 * @author 841898
 */
public class CategoriesDB {

    public Category get(int categoryId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Category category = em.find(Category.class, categoryId);
            return category;

        } finally {
            em.close();
        }
    }

    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;

        } finally {
            em.close();
        }
    }
    
//    public void insert (Category category) throws Exception{
//            EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//
//        try {
////            Category category
//            Item item = category.getItemList().add(category);
//            Category category = item.getCategory().add(category);
//            User user = item.getOwner();
//            user.getItemList().add(item);
//            trans.begin();
//            em.persist(item);
//            em.merge(user);
//            trans.commit();
//        } catch (Exception ex) {
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }
    
    public void update(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
