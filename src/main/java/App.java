

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
        String connectionString = "jdbc:postgresql://localhost:5432/animal_wild;INIT=RUNSCRIPT from 'classpath:DB'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "hyperloop");
        Map<String, Object> model = new HashMap<>();


        get("/",(request, response) -> {
            return new ModelAndView(model,"layout.hbs");
        },new HandlebarsTemplateEngine());

        get("/animals",(request, response) -> {
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/form",(request, response) -> {
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());


        }


    }
