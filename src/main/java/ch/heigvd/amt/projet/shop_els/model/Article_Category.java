package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="selectArticleByCategory", query = "SELECT c.article FROM Article_Category c WHERE c.category in :cat")
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