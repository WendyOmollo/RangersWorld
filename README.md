# Rangers World

## Description
This application is an evacuation application used by rangers to record animals in a sighting that is being cleared.
A ranger can enter the name of an animal and the location they have seen the animal in.
You can see the animals that have been recorded in a table.

## Setup and Installation
##### For your app:
* ``Intellij`` needs to in your machine.
* You can run it using ``Gradle`` or ``Maven.``
* You will also need ``templates `` for your User Interface.
##### For your database:
* You can use ``PostgreSQL``
* ``sudo apt install postgresql postgresql-contrib``
* After installing you navigate into the database by writing ``psql``
##### In creating your tables
* You will also need tables to collect data.
* You first create a database ``CREATE DATABASE animal_wild``
* Then you navigate to the database ``\c animal_wild``
* Then create the tables that you will need:
``CREATE TABLE animals`` , ``CREATE TABLE sightings`` and ``CREATE TABLE endangered_animals``
* Each table will have the input it is taking.
* For reference visit ``Digital Ocean``

#####For deploying to Heroku:
* You will need a ``Procfile`` that has your dyno.This is a dyno >``web:.build/install/Animal/bin/Animal``
* In your ``build.gradle`` you will need some dependencies and tasks
``task stage(dependsOn: ['clean', 'installApp'])``
* You will also need a file called jav that has the following:

```import java.net.URI;
  import java.net.URISyntaxException;
  
  public class jav {
  
      private static URI dbUri;
      public static Sql2o sql2o;
      static Logger logger = LoggerFactory.getLogger(jav.class);
      static {
  
          try {
              if (System.getenv("DATABASE_URL") == null) {
                  dbUri = new URI("postgres://localhost:5432/<your database name>");
              } else {
                  dbUri = new URI(System.getenv("DATABASE_URL"));
              }
              int port = dbUri.getPort();
              String host = dbUri.getHost();
              String path = dbUri.getPath();
              String username = (dbUri.getUserInfo() == null) ? <your username> : dbUri.getUserInfo().split(":")[0];
              String password = (dbUri.getUserInfo() == null) ? <your password> : dbUri.getUserInfo().split(":")[1];
              sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
          } catch (URISyntaxException e ) {
              logger.error("Unable to connect to database.");
          }
  
      }
  }
```
* Don't forget ``create.sql`` and ``drop.sql`` files for your tables and database
* Then add ``Gradle Wrapper `` to github so that Heroku can run your app.
* Then run the following commands on your terminal:
* ``heroku login``
* ``heroku create``
* ``heroku addons:create heroku-postgresql:hobby-dev --app`` plus the random app name that heroku has given you.
* ``heroku pg:push your local database name the database heroku has created --app the random app name``
* ``./gradlew build deployHeroku``

* This deploys your app to Heroku.
In case the app crashed go to sources like Heroku and Medium for help.

## Authors
Wendy Omollo

## Contributors
Wendy Omollo

## Tech used
* Java
* Spark
* PostgreSQL

##License
MIT License

Copyright (c) 2019 Wendy Omollo
