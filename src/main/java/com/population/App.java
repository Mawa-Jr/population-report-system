package com.population;

import java.sql.*;
import java.util.Scanner;

public class App {
    // Database connection settings
    private static final String URL = "jdbc:mysql://host.docker.internal:3307/world";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("=== POPULATION REPORT SYSTEM ===\n");

        // Report 1
        System.out.println("--- REPORT 1: All Countries by Population (Largest to Smallest) ---");
        getAllCountriesByPopulation();

        // Report 2
        System.out.println("\n--- REPORT 2: All Cities by Population (Largest to Smallest) ---");
        getAllCitiesByPopulation();

        // Report 3
        System.out.println("\n--- REPORT 3: Top N Cities by Population ---");
        getTopNCitiesByPopulation();

        System.out.println("\n REPORTS 1-3 COMPLETE!");
    }

    // ==================== REPORT 1 ====================
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

    // ==================== REPORT 2 ====================
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
    // ==================== REPORT 3 ====================
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
}