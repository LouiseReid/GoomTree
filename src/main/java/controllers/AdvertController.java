//package controllers;
//
//import db.DBHelper;
//import models.Advert;
//import models.Category;
//import models.User;
//import spark.ModelAndView;
//import spark.template.velocity.VelocityTemplateEngine;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import static spark.Spark.get;
//import static spark.Spark.post;
//
//
//public class AdvertController {
//
//    public AdvertController() {
//        this.setUpEndPoints();
//    }
//
//    public void setUpEndPoints(){
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Advert> adverts = DBHelper.getAll(Advert.class);
//            User loggedInUser = LoginController.getLoggedInUser(req, res);
//            model.put("adverts", adverts);
//            model.put("user", loggedInUser);
//            model.put("login", "templates/login.vtl");
//            model.put("template", "templates/adverts/index.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());
//
//        post("/", (req, res) -> {
//        User user = DBHelper.find(LoginController.getLoggedInUser(req, res).getId(), User.class);
//        String title = req.queryParams("title");
//        String description = req.queryParams("description");
//        String categoryEnumValue = req.queryParams("category");
//        Category category = Category.valueOf(categoryEnumValue);
//        double price = Double.parseDouble(req.queryParams("price"));
//        String image = req.queryParams("image");
//        Advert advert = new Advert(user, title, description, category, price, image);
//        DBHelper.save(advert);
//        user.addAdvertToPostedAdverts(advert);
//        DBHelper.save(user);
//        res.redirect("/");
//        return null;
//        }, new VelocityTemplateEngine());
//
//        get("adverts/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Category> categories = DBHelper.allCategories();
//            model.put("categories", categories);
//            model.put("template", "templates/adverts/create.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());
//    }
//}
