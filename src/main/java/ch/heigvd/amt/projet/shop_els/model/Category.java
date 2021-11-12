package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="selectAllCategory", query = "SELECT idCategory, name FROM Category "),
        @NamedQuery(name="selectAllArticles", query="SELECT cat from Category cat join Category.articles")
})
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategory")
    private int idCategory;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;

    public Category() {}

    public int getIdCategory() { return idCategory; }

    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Article> getArticles() { return articles; }

    public void setArticles(List<Article> articles) { this.articles = articles; }

}
