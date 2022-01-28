package ch.heigvd.amt.projet.shop_els.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="selectAllArticleCart", query = "SELECT c.article_cart_id, c.article, c.quantity, c.cart FROM Article_Cart c"),
        @NamedQuery(name="selectArticleCartId", query="SELECT article_cart_id FROM Article_Cart ac WHERE ac.article_cart_id in :id")
})

@Entity
@Table(name ="Article_Cart")
public class Article_Cart {
    @Id
    @Column(name = "idArticleCart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int article_cart_id;

    @ManyToOne
    @JoinColumn(name="article")
    private Article article;

    @ManyToOne
    @JoinColumn(name="cart")
    private Cart cart;

    @Column(name="quantity")
    private int quantity;

    public Article_Cart() {}

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
    public int getArticle_cart_id() {
        return article_cart_id;
    }

    public void setArticle_cart_id(int article_cart_id) {
        this.article_cart_id = article_cart_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
