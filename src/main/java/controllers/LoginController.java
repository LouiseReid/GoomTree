package controllers;

import db.DBHelper;
import models.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    public LoginController() {
        this.setUpEndPoints();
    }

    private void setUpEndPoints(){
        post("/login", (req, res) ->{
            String username = req.queryParams("username");
            req.session().attribute("username", username);
            User user = new User(req.session().attribute("username"));
            DBHelper.save(user);
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());


        get("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());
    }


    public static User loggedInUser(Request req){
        if(req.session().attribute("username") != null){
            return DBHelper.currentUser();
        } else {
            User user = new User(req.session().attribute("username"));
            DBHelper.save(user);
            return user;
        }
    }

}
