import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    // <Vishakha> REFACTORED ALL THE REPEATED LINES OF CODE
    // Arrange: Creating a Restaurant object
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    Restaurant mockRestaurant= Mockito.spy(restaurant);// Arrange : Mocking restaurant object

    @ExtendWith(MockitoExtension.class)
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //<Vishakha> AAA testing
        mockRestaurant.openingTime= LocalTime.parse("10:30:00");// Arrange : setting opening time for mock restaurant
        LocalTime twoHoursAfterOpeningTime = mockRestaurant.openingTime.plusHours(2);//Arrange :creating mock current time

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(twoHoursAfterOpeningTime);//Act: returning mock value of current time

        assertTrue(mockRestaurant.isRestaurantOpen());//Assert: Asserting that the test returns true
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //<Vishakha> AAA testing
        mockRestaurant.closingTime= LocalTime.parse("22:30:00");// Arrange: setting closing time for mock restaurant
        LocalTime twoHoursAfterClosingTime = mockRestaurant.closingTime.plusHours(2);// Arrange: creating mock current time

        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(twoHoursAfterClosingTime);//Act: returning mock value of current time

        assertFalse(mockRestaurant.isRestaurantOpen());//Assert: Asserting that the test returns false

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}