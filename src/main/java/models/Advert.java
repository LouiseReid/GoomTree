package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adverts")
public class Advert {

    private int id;
    private User owner;
    private String title;
    private String description;
    private Category category;
    private double price;
    private List<User> favouriters;
    private List<Comment> comments;
    private String image;

    public Advert() {
    }

    public Advert(User owner, String title, String description, Category category, double price, String image) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.image = image;
        this.favouriters = new ArrayList<>();
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "fav_ads",
            joinColumns = {@JoinColumn(name = "advert_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    public List<User> getFavouriters() {
        return favouriters;
    }

    public void setFavouriters(List<User> favouriters) {
        this.favouriters = favouriters;
    }

    @OneToMany(mappedBy = "advert")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addUserToFavouriters(User user){
        this.favouriters.add(user);
    }

    public void removeUserFromFavouriters(User user){
        this.favouriters.remove(user);
    }

    public void addCommentToAdd(Comment comment){
        this.comments.add(comment);
    }
}
