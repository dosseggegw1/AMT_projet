package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name="selectAllCategory", query = "SELECT idCategory, name FROM Category "),
        @NamedQuery(name= "selectAllArticlesCat", query="SELECT cat from Category cat join cat.articleCategories")
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

    //@OneToMany(mappedBy = "categories")
    //private List<ArticleController> articles;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Article_Category> articleCategories = new HashSet<>();

    public Category() {}

    public int getIdCategory() { return idCategory; }
    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    //public List<ArticleController> getArticles() { return articles; }
    //public void setArticles(List<ArticleController> articles) { this.articles = articles; }
}
