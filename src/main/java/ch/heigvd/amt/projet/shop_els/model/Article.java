package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArticle;

    @Column(name = "name", length=50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private float price;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "stock")
    private int stock;

    @ManyToMany
    private List<Category> categories;

    public Article() {}

    public List<Category> getCategories() { return categories; }

    public void setCategories(List<Category> categories) { this.categories = categories; }

    public int getIdArticle() { return idArticle; }

    public void setIdArticle(int idArticle) { this.idArticle = idArticle; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

}
