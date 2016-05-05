import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Restaurant> restaurants = Restaurant.all();
      model.put("restaurants", restaurants);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Restaurant> restaurants = Restaurant.all();
      String userInputRestaurantName = request.queryParams("restaurantName");
      String userInputReview = request.queryParams("review");
      Integer userInputRating = Integer.parseInt(request.queryParams("rating"));
      String userInputCuisine = request.queryParams("cuisine");
      FoodType foodType = new FoodType(userInputCuisine);
      foodType.save();
      Restaurant newRestaurant = new Restaurant(userInputRestaurantName, userInputReview, foodType.getId(), userInputRating);
      newRestaurant.save();

      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/review", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Restaurant> restaurants = Restaurant.all();
      model.put("restaurants", restaurants);
      model.put("template", "templates/review.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
