package models;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    private int id;
    private User user;
    private String text;
    private Advert advert;

    public Comment() {
    }

    public Comment(User user, String text, Advert advert) {
        this.user = user;
        this.text = text;
        this.advert = advert;
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
