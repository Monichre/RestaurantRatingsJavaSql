import java.util.List;
import org.sql2o.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Restaurant {
  private int id;
  private int foodTypeId;
  private String review;
  private String name;
  private int rating;

  public Restaurant(String name, String review, int foodTypeId, int rating) {
    this.name = name;
    this.review = review;
    this.foodTypeId = foodTypeId;
    this.rating = rating;
  }

  public Restaurant(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getReview() {
    return review;
  }

  public int getId() {
    return id;
  }

  public int getFoodTypeId() {
    return foodTypeId;
  }
  public int getRating() {
    return rating;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, review, foodtypeid, name, rating FROM restaurant";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getReview().equals(newRestaurant.getReview()) &&
             this.getId() == newRestaurant.getId() &&
             this.getFoodTypeId() == newRestaurant.getFoodTypeId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurant (name, review, foodtypeid, rating) VALUES (:name, :review, :foodtypeid, :rating)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("review", this.review)
        .addParameter("foodtypeid", this.foodTypeId)
        .addParameter("rating", this.rating)
        .executeUpdate()
        .getKey();
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurant where id=:id";
      Restaurant task = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
      return task;
    }
  }
}
