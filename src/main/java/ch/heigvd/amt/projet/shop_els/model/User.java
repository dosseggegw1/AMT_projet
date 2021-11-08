package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import javax.persistence.Entity;

@NamedQueries({
        @NamedQuery(name="selectUserID", query = "SELECT idUser from User")
})

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "idUser")
    private int idUser;

    @OneToOne(optional = false)
    @JoinColumn(name="cart_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "fk_cart"))
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
