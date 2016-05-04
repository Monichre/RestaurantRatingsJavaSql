import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.time.LocalDateTime;

public class RestaurantTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/test_food", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantQuery = "DELETE FROM restaurant *;";
      String deleteFoodTypeQuery = "DELETE FROM foodtype *;";
      con.createQuery(deleteRestaurantQuery).executeUpdate();
      con.createQuery(deleteFoodTypeQuery).executeUpdate();
    }
  }
  @Test
  public void Restaurant_instantiatesCorrectly_true() {
    Restaurant myRestaurant = new Restaurant("", "", 0, 1);
    assertEquals(true, myRestaurant instanceof Restaurant);
  }

  // @Test
  // public void getName_taskInstantiatesWithReview_String() {
  //   Restaurant myRestaurant = new Restaurant("", "", 0, 1);
  //   assertEquals("name", myRestaurant.getName());
  // }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfReviewsAretheSame() {
    Restaurant firstRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    Restaurant secondRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_returnsTrueIfReviewsAretheSame() {
    Restaurant myRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    Restaurant firstRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
  }

  @Test
  public void save_assignsIdToRestaurantObject() {
    Restaurant myRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(myRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_findsRestaurantInDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Chino Latino", "The Best", 3, 5);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }
  @Test
  public void save_savesFoodTypeIdIntoDB_true() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    myFoodType.save();
    Restaurant myRestaurant = new Restaurant("Chino Latino", "The Best", myFoodType.getId(), 1);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant.getFoodTypeId(), myFoodType.getId());
  }
}
