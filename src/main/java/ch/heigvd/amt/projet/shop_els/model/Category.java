package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import ch.heigvd.amt.projet.shop_els.model.Article;
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

}
