package dao;

import model.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oRestaurantDao implements RestaurantDao {
    private Sql2o sql2o;

    public Sql2oRestaurantDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }



    @Override
    public void add(Restaurant restaurant) {
        String newRestaurantCreated = "INSERT INTO restaurants (name, location, description) VALUES (:name, :location, :description)";
        try(Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(newRestaurantCreated, true)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setId(id);//
        } catch (Sql2oException ex) {
            System.out.println("Error Exits " + ex);
        }
    }

    @Override
    public List<Restaurant> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM restaurants") //raw sql
                    .executeAndFetch(Restaurant.class); //fetch a list
        }
    }
}
