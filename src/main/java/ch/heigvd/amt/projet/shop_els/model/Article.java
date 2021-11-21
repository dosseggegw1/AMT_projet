package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name="selectArticleIdName", query = "SELECT a.idArticle, a.name FROM Article a"),
        @NamedQuery(name= "selectAllArticles", query = "SELECT a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock FROM Article a"),
        @NamedQuery(name="selectImageURL", query="SELECT a.imageURL FROM Article a"),
        @NamedQuery(name="selectArticleAndCategory", query="SELECT a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock, GROUP(Article_Category.idCategory) AS categories FROM Article a LEFT JOIN Article_Category ON Article_Category.idArticle = a.idArticle GROUP BY a.idArticle")
})

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @Column(name = "idArticle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private float price;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "stock")
    private int stock;

    /*
    @OneToMany
    @JoinTable(
            name = "Article_Category",
            joinColumns = @JoinColumn(name = "idArticle"),
            inverseJoinColumns = @JoinColumn(name = "idCategory"))
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    */

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Article_Cart> article_carts;

    @OneToMany(mappedBy = "article")
    private Set<Article_Category> articleCategories = new HashSet<>();

    public Article() {
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Article_Cart> getArticle_carts() {
        return article_carts;
    }

    public void setArticle_carts(List<Article_Cart> article_carts) {
        this.article_carts = article_carts;
    }

}
