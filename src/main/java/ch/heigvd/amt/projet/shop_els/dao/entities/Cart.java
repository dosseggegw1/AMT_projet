package ch.heigvd.amt.projet.shop_els.dao.entities;

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

    /**
     * Retourne l'id d'un panier
     * @return id en int
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * Assigne un id au panier
     * @param idCart
     */
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    /**
     * Retourne l'utilisateur du panier
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Assigne un utilisateur au panier
     * @param user Utilisateur a assigné
     */
    public void setUser(User user) {
        this.user = user;
    }
}
