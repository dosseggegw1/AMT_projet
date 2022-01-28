package ch.heigvd.amt.projet.shop_els.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="selectArticleByCategory", query = "SELECT c.article FROM Article_Category c WHERE c.category in :cat"),
        @NamedQuery(name="selectCategoriesLinkedToArticles", query = "SELECT acat.category.name FROM Article_Category acat"),
        @NamedQuery(name="selectArticleCategoryId", query = "SELECT ac.article_category_id FROM Article_Category ac WHERE ac.article_category_id in :id"),
        @NamedQuery(name= "selectCategoryNameByArticleId", query = "SELECT ac.category.name FROM Article_Category ac WHERE ac.article.idArticle = :articleID"),
        @NamedQuery(name="selectCategoryIdByArticleId", query = "SELECT ac.category.idCategory FROM Article_Category ac WHERE ac.article.idArticle = :articleID"),
        @NamedQuery(name="selectArticleCategoryIdByArticleIdAndCategoryId", query = "SELECT ac.article_category_id FROM Article_Category ac WHERE ac.article.idArticle = :articleID AND ac.category.idCategory = :categoryID")
})

@Entity
@Table(name = "Article_Category")
public class Article_Category {
    @Id
    @Column(name = "idArticleCategory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int article_category_id;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name="idCategory")
    private Category category;

    public Article_Category() {}

    public int getArticle_category_id() { return article_category_id; }
    public void setArticle_category_id(int article_category_id) { this.article_category_id = article_category_id; }
    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}