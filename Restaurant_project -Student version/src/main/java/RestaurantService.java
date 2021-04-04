import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    
    private static List<Item> selectedItems = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
    	
    	for(Restaurant s:getRestaurants()) {
    		if(s.getName()==restaurantName) {
    			return s;
    		} 
    	}
    	throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    
    public long totalOrderValue(String restaurantName,List<Item> listOfItemsSelected) {
		long sum = 0;
    	for(Item i:listOfItemsSelected) {
			sum=sum+i.getPrice();
		 }
    	
    	return sum;
    	
    }
    
    
    
}
