package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    private List<Advert> postedAdverts;
    private List<Advert> favAdverts;
    private List<Comment> comments;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.postedAdverts = new ArrayList<>();
        this.favAdverts = new ArrayList<>();
        this.comments = new ArrayList<>();
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

    @OneToMany(mappedBy = "owner")
    public List<Advert> getPostedAdverts() {
        return postedAdverts;
    }

    public void setPostedAdverts(List<Advert> postedAdverts) {
        this.postedAdverts = postedAdverts;
    }


    @ManyToMany(mappedBy = "favouriters")
    public List<Advert> getFavAdverts() {
        return favAdverts;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setFavAdverts(List<Advert> favAdverts) {
        this.favAdverts = favAdverts;
    }


    public void addAdvertToFavourites(Advert advert){
        this.favAdverts.add(advert);
    }

    public void removeAdvertFromFavourites(Advert advert){
        this.favAdverts.remove(advert);
    }

    public void addAdvertToPostedAdverts(Advert advert){
        this.postedAdverts.add(advert);
    }

    public void addCommentToComments(Comment comment){
        this.comments.add(comment);
    }

}
