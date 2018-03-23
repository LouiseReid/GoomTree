package db;

import models.Advert;
import models.Category;
import models.Comment;
import models.User;

public class Seeds {

    public static void seedData(){
//    public static void main(String[] args) {

        DBHelper.deleteAll(Comment.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Advert.class);

        User user1 = new User("John Lennon");
        User user2 = new User("Paul McCartney");
        User user3 = new User("George Harrison");
        User user4 = new User("Ringo Starr");
        User user5 = new User("Mick Jagger");
        User user6 = new User("Keith Richards");
        User user7 = new User("Ronnie Wood");
        DBHelper.save(user1);
        DBHelper.save(user2);
        DBHelper.save(user3);
        DBHelper.save(user4);
        DBHelper.save(user5);
        DBHelper.save(user6);
        DBHelper.save(user7);

        Advert advert1 = new Advert(user1, "Tub Full of Glass", "<p>Got a tub full of glass in my garden needing shifted</p>", Category.HOUSEHOLD, 5.00);
        Advert advert2 = new Advert(user2, "Stainless Steel Urinal and Basin", "<p>Free to a good home, have new marble ones installed</p>", Category.HOUSEHOLD, 0.00);
        Advert advert3 = new Advert(user3, "Do you want to throw a pie at a guys face?  Now you can!", "<p>So have you ever thought you'd like to throw a pie at someones face like in the movies?  Well now you can!</p><br><br><p>I have always wondered what it would be like to be on the receiveing end of a flung pie so I'd like to be your targer</p>", Category.SPORTS, 10.00);
        Advert advert4 = new Advert(user4, "Soil", "<p>Unwanted soild to be removed from my garden</p>", Category.HOUSEHOLD, 0.00);
        Advert advert5 = new Advert(user5, "Pound Coin Holder", "<p>Impress your friends with this pound coin holder</p><br><p>Made from blue plastic, slots nicely into back pocket or wallet</p>", Category.CLOTHING, 4.00);
        Advert advert6 = new Advert(user6, "Psyhic Readings", "<p>Hi, I offer 60 minute readings for £20.  This is done from my phone</p><br><p>All readings must be paid for in advance</p>", Category.OTHER, 20.00);
        Advert advert7 = new Advert(user7, "Burst Baw", "Authenic English Premier League football", Category.SPORTS, 12.50);
        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);
        DBHelper.save(advert4);
        DBHelper.save(advert5);
        DBHelper.save(advert6);
        DBHelper.save(advert7);

        Comment comment1 = new Comment(user2, "Do you take postal orders?", advert6);
        DBHelper.save(comment1);

        User user = DBHelper.find(user2.getId(), User.class);

        DBHelper.addFavouriterToAdvert(advert1, user3);
        DBHelper.addFavouriterToAdvert(advert1, user5);
        DBHelper.addFavouriterToAdvert(advert3, user1);


    }


}
