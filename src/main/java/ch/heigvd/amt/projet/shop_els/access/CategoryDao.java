package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.model.ModelException;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class CategoryDao implements Dao<Category> {
    private Session session;

    @Override
    public void save(Category category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(category);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Category category) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Category updateCategory = session.get(Category.class, category.getIdCategory());
        updateCategory.setName(category.getName());

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Category get(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, id);

        session.close();
        return category;
    }

    @Override
    public List<Category> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Category> categories = session.getNamedQuery("selectAllCategories").getResultList();

        session.close();
        return categories;
    }

    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, id);
        session.delete(category);
        List list = session.getNamedQuery("selectCategoryId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression de la catégorie");
        }
    }

    public List getAllNames() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List stringList = session.getNamedQuery("selectCategoryName").getResultList();

        session.close();
        return stringList;
    }

    public void checkIfNameExists(String name) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List list = session.getNamedQuery("selectCategoryNameWithName").setParameter("cat", name).getResultList();

        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("La catégorie existe déjà");
        }
    }
}
