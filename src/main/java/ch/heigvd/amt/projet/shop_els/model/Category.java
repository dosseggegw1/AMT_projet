package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

//        @NamedQuery(name= "selectAllArticlesCat", query="SELECT cat from Category cat join cat.articleCategories"),
@NamedQueries({
        @NamedQuery(name="selectAllCategory", query = "SELECT idCategory, name FROM Category "),
        @NamedQuery(name="selectCategoryName", query = "SELECT name FROM Category ")
})
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private int idCategory;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Article> articles = new HashSet<>();

    public Category() {}

    public int getIdCategory() { return idCategory; }

    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public static Category fetchOne(int id){
        Session session = HibUtil.getSessionFactory().openSession();
        Category cat = (Category) session.load(Category.class,id);
        session.close();
        return cat;
    }

    public static List<Category> getCategoryById(int id) {
        Session session = HibUtil.getSessionFactory().openSession();
         List<Category> category = (List<Category>) session.createQuery("SELECT idCategory, name FROM Category WHERE idCategory = :id")
                .setParameter("id", id).list();
         session.close();
         return category;
    }

}
