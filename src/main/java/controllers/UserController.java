package controllers;

import db.DBHelper;
import models.Advert;
import models.User;
import spark.ModelAndView;
import spark.Request;
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
        get("/:id/my_adverts", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            User user = DBHelper.find(intId, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            List<Advert> adverts = DBHelper.usersAdverts(user);
            model.put("adverts", adverts);
            model.put("template", "templates/user/adverts.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    }

}
