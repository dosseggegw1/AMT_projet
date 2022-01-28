package ch.heigvd.amt.projet.shop_els.entities;

import ch.heigvd.amt.projet.shop_els.dao.entities.Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryTest {
    private Session session;

    @Test
    @Order(1)
    void shouldInsertCategoryData() throws Exception {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Query to insert a new category in the table Category
        Category category = new Category();
        category.setName("Bougie");

        session.save(category);

        // Query to get the id of the last inserted category
        String hqlSelect = "FROM Category ORDER BY idCategory DESC";
        Query querySelect = session.createQuery(hqlSelect).setMaxResults(1);
        List<Category> results = querySelect.getResultList();
        int categoryID = results.get(0).getIdCategory();

        session.getTransaction().commit();
        session.close();

        assertEquals(category.getIdCategory(), categoryID);
    }

    @Test
    @Order(2)
    void shouldDeleteCategoryData() {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Query to get the id of the last created category
        String hqlSelect = "FROM Category ORDER BY idCategory DESC";
        Query querySelect = session.createQuery(hqlSelect).setMaxResults(1);
        List<Category> results = querySelect.getResultList();
        int categoryID = results.get(0).getIdCategory();

        // Query to delete the last inserted category from the Category table
        String hqlDelete = "DELETE FROM Category WHERE idCategory = :cat_id";
        Query queryDelete = session.createQuery(hqlDelete);
        queryDelete.setParameter("cat_id", categoryID);
        queryDelete.executeUpdate();

        // Query to find the id of the last category in the table
        String hqlToCompare = "FROM Category ORDER BY idCategory DESC";
        Query querySecondSelect = session.createQuery(hqlToCompare).setMaxResults(1);
        List<Category> resultsToCompare = querySecondSelect.getResultList();
        int lastCategoryID = resultsToCompare.get(0).getIdCategory();

        session.getTransaction().commit();
        session.close();

        // The id of the deleted category should not be found in the table again
        // We compare it with the biggest id found after deletion
        assertNotEquals(categoryID, lastCategoryID);
    }
}
