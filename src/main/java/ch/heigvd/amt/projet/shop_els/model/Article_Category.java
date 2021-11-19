package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="Article_Category")
public class Article_Category {
    @EmbeddedId
    private Article_Category_Id article_category_id;

    @ManyToMany
    @MapsId("fk_idArticle")
    @JoinColumn(name="fk_idArticle")
    private List<Article> articles;

    @ManyToMany
    @MapsId("fk_idCategory")
    @JoinColumn(name="fk_idCategory")
    private List<Category> categories;

    public Article_Category() {}

    public Article_Category_Id getArticle_category_id() { return article_category_id; }
    public void setArticle_category_id(Article_Category_Id article_category_id) { this.article_category_id = article_category_id; }
    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }
    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

}
