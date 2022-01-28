package ch.heigvd.amt.projet.shop_els.entities;

import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static ch.heigvd.amt.projet.shop_els.util.Util.*;

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

    public Article() {}

    /**
     * Récupère l'id d'un article
     * @return l'id
     */
    public int getIdArticle() {
        return idArticle;
    }

    /**
     * Assigne un id à un article
     * @param idArticle
     */
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    /**
     * Retourne le nom de l'article
     * @return nom de l'article en string
     */
    public String getName() {
        return name;
    }

    /**
     * Assigne un nom à l'article
     * @param name Futur nom de l'article
     * @throws ModelException
     */
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

    /**
     * Ajoute une description à l'article
     * @return description de l'article en string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigne une description à l'article
     * @param description
     * @throws ModelException
     */
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

    /**
     * Retourne le prix de l'article
     * @return prix en float
     */
    public float getPrice() {
        return price;
    }

    /**
     * Assigne un prix à l'article
     * @param price
     * @throws ModelException
     */
    public void setPrice(float price) throws ModelException {
        if(price < MINIMUM_QUANTITY) {
            throw new ModelException("Le prix ne peut pas être négatif");
        } else {
            this.price = price;
        }
    }

    /**
     * Retourne l'url de l'image de l'article
     * @return url de l'image en string
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Assigne l'url de l'image à l'article
     * @param imageURL
     * @throws ModelException
     */
    public void setImageURL(String imageURL) throws ModelException {
        if(FilenameUtils.getExtension(imageURL).equals("jpg") ||
        FilenameUtils.getExtension(imageURL).equals("jpeg") ||
        FilenameUtils.getExtension(imageURL).equals("png")) {
            this.imageURL = imageURL;
       } else {
            throw new ModelException("L'extension n'est pas supportée. Nous acceptons seulement jpg, jpeg et png");
        }
    }

    /**
     * Retourne le stock de l'article
     * @return stock en int
     */
    public int getStock() {
        return stock;
    }

    /**
     * Assigne le stock à l'article
     * @param stock
     * @throws ModelException
     */
    public void setStock(int stock) throws ModelException {
        if(stock < MINIMUM_QUANTITY) {
            throw new ModelException("Le stock ne peut pas être négatif");
        } else {
            this.stock = stock;
        }
    }

}
