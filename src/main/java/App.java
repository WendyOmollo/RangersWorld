

import dao.SightingDao;
import models.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/animal_wild";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "hyperloop");
        Map<String, Object> model = new HashMap<>();


        get("/",(request, response) -> {
            return new ModelAndView(model,"layout.hbs");
        },new HandlebarsTemplateEngine());

        get("/animals",(request, response) -> {
//            model.put("sightings", Sighting.getAll().size());
            model.put("animals",Animal.all());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/form",(request, response) -> {
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/form",(request, response) -> {
            String animalName = request.queryParams("name");
            String animalSighting = request.queryParams("sighting");
            String animalRanger = request.queryParams("ranger_name");
//            int animalId = Integer.parseInt(request.queryParams("animalId"));
            Animal animal = new Animal(animalName);
            animal.save();
            Sighting sighting = new Sighting(animalSighting,animalRanger,1);
            sighting.add(sighting);
            model.put("name",animalName);
            model.put("sighting",animalSighting);
            model.put("ranger_name",animalRanger);
            return new ModelAndView(model,"index.hbs");
//                response.redirect("/animals");
//                return null;
        },new HandlebarsTemplateEngine());

        }


    }
