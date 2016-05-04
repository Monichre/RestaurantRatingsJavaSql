import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class FoodTypeTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/test_food", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantQuery = "DELETE FROM restaurant *;";
      String deleteCategoriesQuery = "DELETE FROM foodtype *;";
      con.createQuery(deleteRestaurantQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }
  @Test
  public void FoodType_instantiatesCorrectly_true() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    assertEquals(true, myFoodType instanceof FoodType);
  }

  @Test
  public void getName_categoryInstantiatesWithName_String() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    assertEquals("Asian Fusion", myFoodType.getType());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(FoodType.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    FoodType firstFoodType = new FoodType("Asian Fusion");
    FoodType secondFoodType = new FoodType("Asian Fusion");
    assertTrue(firstFoodType.equals(secondFoodType));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    myFoodType.save();
    assertTrue(FoodType.all().get(0).equals(myFoodType));
  }

  @Test
  public void save_assignsIdToObject() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    myFoodType.save();
    FoodType savedFoodType = FoodType.all().get(0);
    assertEquals(myFoodType.getId(), savedFoodType.getId());
  }

  @Test
  public void find_findFoodTypeInDatabase_true() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    myFoodType.save();
    FoodType savedFoodType = FoodType.find(myFoodType.getId());
    assertTrue(myFoodType.equals(savedFoodType));
  }

  @Test
  public void getRestaurants_retrievesAllRestaurantsFromDatabase_tasksList() {
    FoodType myFoodType = new FoodType("Asian Fusion");
    myFoodType.save();
    Restaurant firstRestaurant = new Restaurant("Chino Latino", "The Best", myFoodType.getId(), 1);
    firstRestaurant.save();
    Restaurant secondRestaurant = new Restaurant("Chino Latino", "The Best", myFoodType.getId(), 1);
    secondRestaurant.save();
    Restaurant[] restaurants = new Restaurant[] { firstRestaurant, secondRestaurant };
    assertTrue(myFoodType.getRestaurantsbyFoodType().containsAll(Arrays.asList(restaurants)));
  }

}
