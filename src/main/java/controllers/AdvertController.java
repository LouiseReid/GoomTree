package controllers;

import db.DBHelper;
import models.Advert;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;


public class AdvertController {

    public AdvertController() {
        this.setUpEndPoints();
    }

    public void setUpEndPoints(){
        get("/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Advert> adverts = DBHelper.getAll(Advert.class);
            model.put("adverts", adverts);
            model.put("template", "templates/adverts/index.vtl");
            return new ModelAndView(model, "templates/layout");
        }, new VelocityTemplateEngine());
    }
}
