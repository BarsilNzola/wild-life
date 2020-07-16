import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import models.*;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("healthy", EndangeredAnimal.HEALTHY);
            model.put("ill", EndangeredAnimal.ILL);
            model.put("okay", EndangeredAnimal.OKAY);

            model.put("newborn", EndangeredAnimal.NEWBORN);
            model.put("young", EndangeredAnimal.YOUNG);
            model.put("adult", EndangeredAnimal.ADULT);


            return new ModelAndView(model, "animal-add-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            Sighting newSighting = new Sighting(animalId, location, rangerName);
            newSighting.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightingList = Sighting.all();
            model.put("sightings", sightingList);
            return new ModelAndView(model, "listed-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //get new ranger form
        get("/ranger/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "newranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ranger", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String number = request.queryParams("number");
            int badgeNumber = Integer.parseInt(request.queryParams("badgeNumber"));
            Ranger ranger = new Ranger(name, number, badgeNumber);
            ranger.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/ranger", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangerList = Ranger.all();
            model.put("rangers", rangerList);
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
