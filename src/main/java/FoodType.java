import java.util.List;
import org.sql2o.*;

public class FoodType {
  private int id;
  private String type;

  public FoodType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public int getId() {
    return id;
  }

  public static List<FoodType> all() {
    String sql = "SELECT id, type FROM foodtype";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(FoodType.class);
    }
  }

  public List<Restaurant> getRestaurantsbyFoodType() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM restaurant WHERE foodTypeId=:id ORDER BY rating DESC";
     return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Restaurant.class);
   }
 }

  @Override
  public boolean equals(Object otherFoodType) {
    if (!(otherFoodType instanceof FoodType)) {
      return false;
    } else {
      FoodType newFoodType = (FoodType) otherFoodType;
      return this.getType().equals(newFoodType.getType()) &&
             this.getId() == newFoodType.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO foodtype(type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  public static FoodType find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM foodtype where id=:id";
      FoodType category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(FoodType.class);
      return category;
    }
  }
}
