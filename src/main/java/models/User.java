package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    private List<Advert> postedAdverts;
    private List<Comment> comments;
    private List<Advert> favourites;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.postedAdverts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.favourites = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Advert> getPostedAdverts() {
        return postedAdverts;
    }

    public void setPostedAdverts(List<Advert> postedAdverts) {
        this.postedAdverts = postedAdverts;
    }


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(mappedBy = "favouriters")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Advert> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Advert> favourites) {
        this.favourites = favourites;
    }

    public void addAdvertToPostedAdverts(Advert advert){
        this.postedAdverts.add(advert);
    }

    public void addCommentToComments(Comment comment){
        this.comments.add(comment);
    }

    public void addAdvertToFavourites(Advert advert){
        this.favourites.add(advert);
    }

}
