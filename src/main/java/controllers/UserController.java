package controllers;

import db.DBHelper;
import models.Advert;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController() {
        this.setUpEndPoints();
    }

    public void setUpEndPoints(){
        get("/my_adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            User loggedInUser = LoginController.getLoggedInUser(req, res);
            model.put("user", loggedInUser);
            List<Advert> adverts = DBHelper.usersAdverts(loggedInUser);
            model.put("adverts", adverts);
            model.put("template", "templates/user/adverts.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
