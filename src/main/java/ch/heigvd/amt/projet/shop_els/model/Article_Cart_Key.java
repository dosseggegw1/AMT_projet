package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Article_Cart_Key implements java.io.Serializable {
    @Column(name="fk_idArticle")
    private int fk_idArticle;

    @Column(name="fk_idCart")
    private int fk_idCart;
}
