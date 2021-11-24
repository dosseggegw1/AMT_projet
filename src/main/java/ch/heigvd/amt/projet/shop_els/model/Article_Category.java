package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;

@Entity
@Table(name ="Article_Category", indexes = {
        @Index(name = "fk_article", columnList = "idArticle"),
        @Index(name = "fk_article", columnList = "idCategory")
})
public class Article_Category {

    @EmbeddedId
    private Article_Category_Id id;

    public Article_Category_Id getId() {
        return id;
    }

    public void setId(Article_Category_Id id ) {
        this.id = id;
    }

    public Article_Category() {}
}
