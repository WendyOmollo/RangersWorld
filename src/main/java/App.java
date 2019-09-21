


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
            model.put("sightings", Sighting.getAll().size());
            model.put("animals",Animal.all());
            model.put("endangered",EndangeredAnimal.allEndangered());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/form",(request, response) -> {
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/form",(request, response) -> {
            String animalName = request.queryParams("name");
            String animalLocation = request.queryParams("sighting");
            String animalRanger = request.queryParams("ranger_name");
            Animal animal = new Animal(animalName);
            animal.save();
            Sighting sighting = new Sighting(animalLocation,animalRanger);
            sighting.add();
            model.put("name",animalName);
            model.put("sighting",animalLocation);
            model.put("ranger_name",animalRanger);
                response.redirect("/animals");
                return null;
        },new HandlebarsTemplateEngine());

        post("/form",(request, response) -> {
            String endangeredAnimalName = request.queryParams("name");
            String endangeredAnimalLocation = request.queryParams("location");
            String endangeredAnimalHealth = request.queryParams("health");
            String endangeredAnimalAge = request.queryParams("age");
            String endangeredAnimalRanger = request.queryParams("ranger_name");
            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(endangeredAnimalName,endangeredAnimalHealth,endangeredAnimalAge,"Endangered");
            endangeredAnimal.save();
            Sighting sighting = new Sighting(endangeredAnimalLocation,endangeredAnimalRanger);
            sighting.add();
            model.put("name",endangeredAnimalName);
            model.put("location",endangeredAnimalLocation);
            model.put("health",endangeredAnimalHealth);
            model.put("age",endangeredAnimalAge);
            model.put("ranger_name",endangeredAnimalRanger);
            response.redirect("/animals");
            return null;
        },new HandlebarsTemplateEngine());

        }


    }
