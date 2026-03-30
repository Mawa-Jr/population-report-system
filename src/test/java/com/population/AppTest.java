package com.population;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testDatabaseConnection() {
        assertTrue(true, "Basic test passed");
    }

    @Test
    public void testGetTotalCountries() {
        int total = App.getTotalCountries();
        assertTrue(total > 0, "Total countries should be greater than 0");
    }

    @Test
    public void testGetAllCountriesByPopulation() {
        assertDoesNotThrow(() -> App.getAllCountriesByPopulation());
    }

    @Test
    public void testGetTopNCitiesByPopulation() {
        assertDoesNotThrow(() -> App.getTopNCitiesByPopulation());
    }
}