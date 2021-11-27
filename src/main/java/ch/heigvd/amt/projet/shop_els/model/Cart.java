package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@NamedQueries({
        @NamedQuery(name= "selectAllCartId", query = "SELECT idCart FROM Cart"),
        @NamedQuery(name="selectAllCart", query="SELECT idCart, user FROM Cart"),
        @NamedQuery(name="selectCartId", query="SELECT idCart FROM Cart c WHERE c.idCart in :id")
})

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCart")
    private int idCart;

    @OneToOne(mappedBy = "fk_cart", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Article_Cart> article_carts = new HashSet<>();

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

    public Set<Article_Cart> getArticle_carts() {
        return article_carts;
    }

    public void setArticle_carts(Set<Article_Cart> article_carts) {
        this.article_carts = article_carts;
    }



}
