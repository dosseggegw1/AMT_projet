package ch.heigvd.amt.projet.shop_els.model;

import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static ch.heigvd.amt.projet.shop_els.util.Constants.*;

@NamedQueries({
        @NamedQuery(name="selectArticleIdName", query = "SELECT a.idArticle, a.name FROM Article a"),
        @NamedQuery(name= "selectAllArticles", query = "SELECT a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock FROM Article a"),
        @NamedQuery(name="selectImageURL", query="SELECT a.imageURL FROM Article a"),
        @NamedQuery(name="selectArticleName", query="SELECT a.name FROM Article a WHERE a.name = :art"),
        @NamedQuery(name="selectArticleNameDescription", query="SELECT a.name, a.description FROM Article a WHERE a.description in :descr"),
        @NamedQuery(name="selectArticleAndCategory", query="SELECT a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock, acat.category.idCategory, acat.category.name FROM Article a LEFT JOIN Article_Category acat ON acat.article.idArticle = a.idArticle"),
        @NamedQuery(name="selectArticleAndCategoryById", query="SELECT a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock, acat.category.idCategory, acat.category.name FROM Article a LEFT JOIN Article_Category acat ON acat.article.idArticle = a.idArticle WHERE acat.article.idArticle = :articleID"),
        @NamedQuery(name="selectArticleId", query="SELECT a.idArticle FROM Article a WHERE a.idArticle in :id"),
        @NamedQuery(name="selectAllArticlesName", query="SELECT a.name FROM Article a"),
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

    @OneToMany(mappedBy = "article")
    private Set<Article_Cart> article_carts = new HashSet<>();

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

    public Set<Article_Category> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(Set<Article_Category> articleCategories) {
        this.articleCategories = articleCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelException {
        if(name.equals("")) {
            throw new ModelException("Le nom de l'article est vide");
        } else if(name.length() > MAXIMUM_NAME_LENGTH) {
            throw new ModelException("Le nom de l'article est trop grand ! (50 caractères maximum)");
        }
        else {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws ModelException {
        if(description.equals("")) {
            throw new ModelException("La description de l'article est vide");
        } else if(description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new ModelException("La description de l'article est trop grand ! (255 caractères maximum)");
        }
        else {
            this.description = description;
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws ModelException {
        if(price < MINIMUM_QUANTITY) {
            throw new ModelException("Le prix ne peut pas être négatif");
        } else {
            this.price = price;
        }
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) throws ModelException {
        if(FilenameUtils.getExtension(imageURL).equals("jpg") ||
        FilenameUtils.getExtension(imageURL).equals("jpeg") ||
        FilenameUtils.getExtension(imageURL).equals("png")) {
            this.imageURL = imageURL;
       } else {
            throw new ModelException("L'extension n'est pas supportée. Nous acceptons seulement jpg, jpeg et png");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws ModelException {
        if(stock < MINIMUM_QUANTITY) {
            throw new ModelException("Le stock ne peut pas être négatif");
        } else {
            this.stock = stock;
        }
    }

    public Set<Article_Cart> getArticle_carts() {
        return article_carts;
    }

    public void setArticle_carts(Set<Article_Cart> article_carts) {
        this.article_carts = article_carts;
    }
}
