package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name="selectAllCategory", query = "SELECT idCategory, name FROM Category "),
        @NamedQuery(name= "selectAllArticlesCat", query="SELECT cat from Category cat join cat.articleCategories"),
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Article_Category> articleCategories = new HashSet<>();

    public Category() {}

    public int getIdCategory() { return idCategory; }
    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Article_Category> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(Set<Article_Category> articleCategories) {
        this.articleCategories = articleCategories;
    }

}
