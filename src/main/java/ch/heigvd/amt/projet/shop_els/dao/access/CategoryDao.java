package ch.heigvd.amt.projet.shop_els.dao.access;

import ch.heigvd.amt.projet.shop_els.dao.entities.Category;
import ch.heigvd.amt.projet.shop_els.dao.entities.ModelException;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import java.util.List;

public class CategoryDao implements Dao<Category> {
    private Session session;

    /**
     * Sauvergarde d'une catégorie dans la base de données
     * @param category catégorie à sauvegarder
     */
    @Override
    public void save(Category category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(category);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Mise à jour d'une catégorie dans la base de données
     * @param category catégorie à mettre à jour
     * @throws ModelException
     */
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

    /**
     * Récupération d'une catégorie selon son identifiant
     * @param id identifiant de la catégorie à récupérer
     * @return un objet de type Category
     * @throws DaoException
     */
    @Override
    public Category get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, id);

        session.close();
        if(category == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return category;
    }

    /**
     * Récupération de toutes les catégories de la base de données
     * @return une liste d'objets Category
     */
    @Override
    public List<Category> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Category> categories = session.getNamedQuery("selectAllCategories").getResultList();

        session.close();
        return categories;
    }

    /**
     * Suppression d'une catégorie selon son identifiant
     * @param id identifiant de la catégorie à supprimer
     * @throws DaoException
     */
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

    /**
     * Récupération des noms de toutes les catégories
     * @return la liste des noms des catégories
     */
    public List getAllNames() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List stringList = session.getNamedQuery("selectCategoryName").getResultList();

        session.close();
        return stringList;
    }

    /**
     * Vérification de l'existence du nom d'une catégorie dans la base de données
     * @param name nom à rechercher
     * @throws DaoException
     */
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
