package com.population;

import java.sql.*;
import java.util.Scanner;

public class App {
    // Database connection settings
    private static final String URL = "jdbc:mysql://host.docker.internal:3307/world";
    private static final String USER = "docker";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("POPULATION REPORT SYSTEM\n");

        // Report 1
        System.out.println("--- REPORT 1: View all Countries by Population (Largest to Smallest) ---");
        getAllCountriesByPopulation();

        // Report 2
        System.out.println("\n--- REPORT 2: View all Cities by Population (Largest to Smallest) ---");
        getAllCitiesByPopulation();

        // Report 3
        System.out.println("\n--- REPORT 3: View all Top N Cities by Population ---");
        getTopNCitiesByPopulation();

        // Report 4
        System.out.println("\n--- REPORT 4: View all Capital Cities by Continent ---");
        getCapitalsByContinent();

        // Report 5
        System.out.println("\n--- REPORT 5: Population Breakdown by Continent ---");
        getPopulationBreakdownByContinent();

        System.out.println("\n REPORTS 1-5 COMPLETE!");
    }

    // REPORT 1
    // All countries in the world organised by largest population to smallest
    public static void getAllCountriesByPopulation() {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country " +
                "ORDER BY Population DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-5s %-30s %-15s %-20s %-15s %-10s%n",
                    "Code", "Name", "Continent", "Region", "Population", "Capital");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                long population = rs.getLong("Population");
                int capital = rs.getInt("Capital");

                System.out.printf("%-5s %-30s %-15s %-20s %-15d %-10d%n",
                        code, name, continent, region, population, capital);
                count++;
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getTotalCountries() {
        String sql = "SELECT COUNT(*) FROM country";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // REPORT 2
    // All cities in the world organised by largest population to smallest
    public static void getAllCitiesByPopulation() {
        String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-35s %-15s %-35s %-15s%n",
                    "City Name", "Country", "District", "Population");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                String district = rs.getString("District");
                long population = rs.getLong("Population");

                System.out.printf("%-35s %-15s %-35s %-15d%n",
                        name, country, district, population);
                count++;
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getTotalCities() {
        String sql = "SELECT COUNT(*) FROM city";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // REPORT 3
    // Top N populated cities in the world where N is provided by the user
    public static void getTopNCitiesByPopulation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of top cities to display (N): ");

        int n;
        try {
            n = Integer.parseInt(scanner.nextLine());
            if (n <= 0) {
                System.out.println("Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, n);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("\n%-35s %-15s %-35s %-15s%n",
                    "City Name", "Country", "District", "Population");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                String district = rs.getString("District");
                long population = rs.getLong("Population");

                System.out.printf("%-35s %-15s %-35s %-15d%n",
                        name, country, district, population);
                count++;
            }

            if (count == 0) {
                System.out.println("No cities found.");
            } else {
                System.out.println("\n(Showing top " + count + " cities)");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // REPORT 4
    // All capital cities in a continent organised by largest population to smallest
    public static void getCapitalsByContinent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter continent (e.g., Asia, Europe, Africa, North America, South America, Australia, Antarctica): ");
        String continentInput = scanner.nextLine().trim();

        if (continentInput.isEmpty()) {
            System.out.println("Please enter a continent.");
            return;
        }

        String continent = null;
        String[] continents = {"Asia", "Europe", "Africa", "North America", "South America", "Australia", "Antarctica"};

        for (String c : continents) {
            if (c.equalsIgnoreCase(continentInput)) {
                continent = c;
                break;
            }
        }

        if (continent == null) {
            System.out.println("Invalid continent. Try: Asia, Europe, Africa, North America, South America, Australia, Antarctica");
            return;
        }

        String sql = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.ID = country.Capital AND country.Continent = ? " +
                "ORDER BY city.Population DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, continent);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("\n%-40s %-30s %-15s%n",
                    "Capital City", "Country", "Population");
            System.out.println("-------------------------------------------------------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                long population = rs.getLong("Population");

                System.out.printf("%-40s %-30s %-15d%n",
                        name, country, population);
                count++;
            }

            if (count == 0) {
                System.out.println("No capital cities found for continent: " + continent);
            } else {
                System.out.println("\n(Found " + count + " capital cities in " + continent + ")");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // REPORT 5
    // Population breakdown by continent (total, city, non-city with percentages)
    public static void getPopulationBreakdownByContinent() {
        String sql = "SELECT " +
                "    continent, " +
                "    SUM(country.Population) AS total_population, " +
                "    COALESCE(SUM(city_population.city_pop), 0) AS city_population " +
                "FROM country " +
                "LEFT JOIN ( " +
                "    SELECT CountryCode, SUM(Population) AS city_pop " +
                "    FROM city " +
                "    GROUP BY CountryCode " +
                ") AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY continent " +
                "ORDER BY total_population DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-20s %-20s %-30s %-30s%n",
                    "Continent", "Total Population", "City Population (%)", "Non-City Population (%)");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String continent = rs.getString("continent");
                long totalPopulation = rs.getLong("total_population");
                long cityPopulation = rs.getLong("city_population");
                long nonCityPopulation = totalPopulation - cityPopulation;

                double cityPercent = (totalPopulation > 0) ? (cityPopulation * 100.0 / totalPopulation) : 0;
                double nonCityPercent = (totalPopulation > 0) ? (nonCityPopulation * 100.0 / totalPopulation) : 0;

                System.out.printf("%-20s %, -20d %, -20d (%.2f%%)   %, -20d (%.2f%%)%n",
                        continent,
                        totalPopulation,
                        cityPopulation, cityPercent,
                        nonCityPopulation, nonCityPercent);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}