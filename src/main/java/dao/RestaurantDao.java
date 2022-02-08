package dao;

import model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void add(Restaurant restaurant);
    List<Restaurant> getAll();

//    FindRestaurantById
//    DeleteEachRestaurant;
}
