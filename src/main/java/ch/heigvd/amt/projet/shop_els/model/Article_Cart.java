package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="selectAllArticleCart", query = "SELECT article_cart_id.fk_idArticle, article_cart_id.fk_idCart, quantity FROM Article_Cart")
})

@Entity
@Table(name ="Article_Cart")
public class Article_Cart {

    @EmbeddedId
    private Article_Cart_Id article_cart_id;

    @ManyToOne
    @MapsId("fk_idArticle")
    @JoinColumn(name="fk_idArticle")
    private Article article;

    @ManyToOne
    @MapsId("fk_idCart")
    @JoinColumn(name="fk_idCart")
    private Cart cart;

    @Column(name="quantity")
    private int quantity;

    public Article_Cart() {}

    public Article_Cart_Id getArticle_cart_id() {
        return article_cart_id;
    }

    public void setArticle_cart_id(Article_Cart_Id article_cart_id) {
        this.article_cart_id = article_cart_id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
