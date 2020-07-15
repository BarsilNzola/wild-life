import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import models.Animal;
import models.Sighting;
import models.DatabaseManagement;
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
            return new ModelAndView(model, "listed-sighting.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
