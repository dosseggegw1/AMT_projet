package ch.heigvd.amt.projet.shop_els.Model;

import javax.persistence.*;
import javax.persistence.Entity;

@NamedQueries({
        @NamedQuery(name="selectUserEmail", query = "SELECT emailAddress from User")
})

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "emailAddress", length=50)
    private String emailAddress;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName", length=50)
    private String firstName;
    @Column(name = "lastName", length=50)
    private String lastName;


    public User() {}


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
