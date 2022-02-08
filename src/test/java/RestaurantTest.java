import org.sql2o.Sql2o;

public class RestaurantTest {
    String connectmetodatabase = "jdbc:postgresql://localhost:5432/restaurantTest";
    Sql2o sql2o = new Sql2o(connectmetodatabase, "postgres", "george");
}
