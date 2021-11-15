package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Article_Cart_Id implements Serializable {
    @Column(name = "fk_idCart")
    int fk_idCart;

    @Column(name = "fk_idArticle")
    int fk_idArticle;

    public Article_Cart_Id() {}

    public int getCart() {
        return fk_idCart;
    }
    public void setCart(int cart) {
        this.fk_idCart = cart;
    }
    public int getArticle() {
        return fk_idArticle;
    }
    public void setArticle(int article) {
        this.fk_idArticle = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article_Cart_Id that = (Article_Cart_Id) o;
        return fk_idCart == that.fk_idCart && fk_idArticle == that.fk_idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fk_idCart, fk_idArticle);
    }
}
