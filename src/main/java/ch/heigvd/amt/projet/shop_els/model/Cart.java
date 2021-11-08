package ch.heigvd.amt.projet.shop_els.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="selectCartID", query = "SELECT idCart from Cart")
})

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idCart")
    private int idCart;

    @OneToOne(mappedBy = "Cart")
    private User user;

    @ManyToMany()
    private List<Article> articles;

    public Cart() { }

    public int getIdCart() { return idCart; }

    public void setIdCart(int idCart) { this.idCart = idCart; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<Article> getArticles() {  return articles; }

    public void setArticles(List<Article> article) { this.articles = article;  }


}
