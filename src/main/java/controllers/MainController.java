package controllers;

import db.Seeds;

public class MainController {

    public static void main(String[] args) {
        AdvertController advertController = new AdvertController();
        Seeds.seedData();
    }
}