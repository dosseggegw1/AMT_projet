package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;

@Entity
@Table(name ="Article_Cart")
public class Article_Cart {

    @EmbeddedId
    Article_Cart_Key id;

    @ManyToOne
    @MapsId("fk_idArticle")
    @JoinColumn(name = "fk_idArticle")
    Article article;

    @ManyToOne
    @MapsId("fk_idCart")
    @JoinColumn(name = "fk_idCart")
    Cart cart;

    @Column(name = "quantity")
    int quantity;

    public Article_Cart() { }

    public Article_Cart_Key getId() { return id; }

    public void setId(Article_Cart_Key id) { this.id = id; }

    public Article getArticle() { return article; }

    public void setArticle(Article article) { this.article = article; }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

}
