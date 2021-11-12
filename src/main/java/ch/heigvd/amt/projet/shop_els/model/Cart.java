package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="selectCartID", query = "SELECT idCart FROM Cart"),
        @NamedQuery(name="selectAllCart", query="SELECT idCart, user FROM Cart")
})

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @Column(name="idCart")
    private int idCart;

    @OneToOne(mappedBy = "fk_cart", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Article_Cart> article_carts;

    public Cart() { }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article_Cart> getArticle_carts() {
        return article_carts;
    }

    public void setArticle_carts(List<Article_Cart> article_carts) {
        this.article_carts = article_carts;
    }

}
