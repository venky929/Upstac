import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestaurantServiceTest {
	//REFACTOR ALL THE REPEATED LINES OF CODE
    static RestaurantService service = new RestaurantService();
    static LocalTime openingTime = LocalTime.parse("10:30:00");
    static LocalTime closingTime = LocalTime.parse("22:00:00");
	static Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);  


    @BeforeAll
    public static void beforeTestAlltestCases() {
    	service.addRestaurant("Paradise", "Hyderabad", openingTime, closingTime);
    	service.addRestaurant("Mehfil", "Hyderabad", openingTime, closingTime);
    	service.addRestaurant("Behrouz", "Hyderabad", openingTime, closingTime);
        service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    @Order(1)
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
   
    	//Checking for restaurant Object Present in List of restaurants  
   assertEquals(restaurant.getName(), service.findRestaurantByName(restaurant.getName()).getName());
    
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content

    @Test
    @Order(2)
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
    
    	Restaurant result = new Restaurant("Domino's", "Hyderabad", openingTime, closingTime);	
    	assertThrows(restaurantNotFoundException.class, ()->service.findRestaurantByName(result.getName()));
    }
  
    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    @Order(3)
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    @Order(5)
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    @Order(4)
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    ///<<<<<<<<<<<<<<<<<<<<TotalPriceForSelectedItems>>>>>>>>>>>>>>>>>>>>>>>///
	@Test
	public void totalPriceForItemsSelected() {
		Restaurant selectedRestaurant =new Restaurant("Charan Cafe", "Hyderabad", openingTime, closingTime);
		selectedRestaurant.addToMenu("Sweet corn soup", 119);
		selectedRestaurant.addToMenu("Vegetable lasagne", 269);
		selectedRestaurant.addToMenu("chicken65", 250);
		selectedRestaurant.addToMenu("biryani", 300);
		Item firstItem = new Item("Sweet corn soup", 119);
		Item secondItem = new Item("Vegetable lasagne", 269);
		List<Item> listOfSelecteditems = new ArrayList<>(); 
		listOfSelecteditems.add(firstItem);
		listOfSelecteditems.add(secondItem);
		//asserting total to 388 as the items selected are two with prices 119 and 269 so total is 388
		assertEquals(388, service.totalOrderValue(selectedRestaurant.getName(), listOfSelecteditems));		
		
	}





}