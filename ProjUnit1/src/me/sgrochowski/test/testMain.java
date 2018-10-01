package me.sgrochowski.test;

import me.sgrochowski.Main;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMain {
    @Test
    public void testCities(){
        Main testing = new Main();
        testing.getCountries();
        assertEquals(1, testing.getCities(0), "There should be one city for the country on index 0.");

    }

    @Test
    public void testCountries(){
        Main testing = new Main();
        testing.getCountries();
        testing.getItems();
        assertEquals("Zimbabwe", testing.getCountry(130), "The last country stored in the list should be Zimbabwe.");
    }
    @Test
    public void testStuff(){
        Main testing = new Main();
        testing.getCountries();
        testing.getItems();
        assertEquals(4, testing.getProducts(2), "The ammount of stuff from the country in index two should be 4.");
    }
}
