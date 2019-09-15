package dao;

import models.Animal;
import org.sql2o.*;
import java.util.List;

public class Sql2oAnimalDao implements AnimalDao{

    private final Sql2o sql2o;

    public Sql2oAnimalDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Animal animal){
        String sql = "INSERT INTO animals(id,name) VALUES (:id,:name)";
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true)
                    .bind(animal)
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
