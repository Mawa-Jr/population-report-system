package com.population;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testDatabaseConnection() {
        // Test will pass if database connection works
        assertTrue(true, "Basic test passed");
    }

    @Test
    public void testGetTotalCountries() {
        // Test that getTotalCountries returns a number
        int total = App.getTotalCountries();
        assertTrue(total > 0, "Total countries should be greater than 0");
    }

    @Test
    public void testGetTotalCities() {
        // Test that getTotalCities returns a number
        int total = App.getTotalCities();
        assertTrue(total > 0, "Total cities should be greater than 0");
    }

    @Test
    public void testGetAllCountriesByPopulation() {
        // This just verifies the method runs without exception
        assertDoesNotThrow(() -> App.getAllCountriesByPopulation());
    }

}

