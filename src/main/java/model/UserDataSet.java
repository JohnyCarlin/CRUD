package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserDataSet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet(Integer id, String name, String email, String country) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setCountry(country);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + "\'" +
                ", email='" + email + '\'' +
                ", country='" + country + "\'" +
                "}";
    }
}
