package controllers;

import db.Seeds;

import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("/public");
        AdvertController advertController = new AdvertController();
        UserController userController = new UserController();
        LoginController loginController = new LoginController();
//        Seeds.seedData();
    }
}
