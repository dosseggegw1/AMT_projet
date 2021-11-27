package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import javax.persistence.Entity;

@NamedQueries({
        @NamedQuery(name="selectAllUser", query = "SELECT idUser, fk_cart from User"),
        @NamedQuery(name="selectUserId", query="SELECT idUser FROM User u WHERE u.idUser in :id")
})

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "idUser")
    private int idUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cart", referencedColumnName = "idCart")
    private Cart fk_cart;

    public User() {}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Cart getFk_cart() { return fk_cart; }

    public void setFk_cart(Cart cart) { this.fk_cart = cart; }

}
