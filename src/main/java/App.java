


import models.*;

import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {

    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(getHerokuAssignedPort());
        String connectionString = "jdbc:postgresql://mjitiwcupfliax:98e65b8eda766e5d268411e9f950e0eda8f1749467da91c60fc90f0396ab7b5d@ec2-107-21-120-104.compute-1.amazonaws.com:5432/dm6c18onevhrn";
        Sql2o sql2o = new Sql2o(connectionString, "mjitiwcupfliax", "98e65b8eda766e5d268411e9f950e0eda8f1749467da91c60fc90f0396ab7b5d");
        Map<String, Object> model = new HashMap<>();


        get("/",(request, response) -> {
            return new ModelAndView(model,"layout.hbs");
        },new HandlebarsTemplateEngine());

        get("/animals",(request, response) -> {
            model.put("sightings", sighting.getAll().size());
            model.put("animals", animal.all());
            model.put("endangered", endangeredanimal.allEndangered());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/form",(request, response) -> {
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/form",(request, response) -> {
            String animalName = request.queryParams("name");
            String animalLocation = request.queryParams("sighting");
            String animalRanger = request.queryParams("ranger_name");
            animal animal = new animal(animalName);
            animal.save();
            sighting sighting = new sighting(animalLocation,animalRanger);
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
            endangeredanimal endangeredAnimal = new endangeredanimal(endangeredAnimalName,endangeredAnimalHealth,endangeredAnimalAge,"Endangered");
            endangeredAnimal.save();
            sighting sighting = new sighting(endangeredAnimalLocation,endangeredAnimalRanger);
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
