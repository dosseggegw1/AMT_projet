package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Article_Category_Id implements Serializable {
    @Column(name = "fk_idArticle")
    int fk_idArticle;

    @Column(name = "fk_idCategory")
    int fk_idCategory;

    public Article_Category_Id(){}

    public int getFk_idArticle() {
        return fk_idArticle;
    }

    public void setFk_idArticle(int fk_idArticle) {
        this.fk_idArticle = fk_idArticle;
    }

    public int getFk_idCategory() {
        return fk_idCategory;
    }

    public void setFk_idCategory(int fk_idCategory) {
        this.fk_idCategory = fk_idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article_Category_Id that = (Article_Category_Id) o;
        return fk_idCategory == that.fk_idCategory && fk_idArticle == that.fk_idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fk_idCategory, fk_idArticle);
    }

}
