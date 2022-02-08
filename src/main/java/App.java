import dao.Sql2oRestaurantDao;
import model.Restaurant;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
//        String connectmetodatabase = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        String connectmetodatabase = "jdbc:postgresql://localhost:5432/restaurant";
        Sql2o sql2o = new Sql2o(connectmetodatabase, "postgres", "george");
        Sql2oRestaurantDao sql2oRestaurantDao = new Sql2oRestaurantDao(sql2o);

        //get: show all tasks in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Restaurant> restaurants = sql2oRestaurantDao.getAll();
            model.put("restaurants", restaurants);
            return new ModelAndView(model, "layout.hbs");
        }, new HandlebarsTemplateEngine());

//        get("createRestaurants", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "createRestaurants.hbs");
//        }, new HandlebarsTemplateEngine());

        post("createRestaurant", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String description = request.queryParams("description");
            Restaurant newRestaurant = new Restaurant(name, location, description);
            sql2oRestaurantDao.add(newRestaurant);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
