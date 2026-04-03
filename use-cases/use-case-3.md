Use Case 3: View Top N Cities by Population

CHARACTERISTIC INFORMATION

Goal in Context
As a data analyst, I want to enter a number N and view the top N populated cities in the world, so that I can customize how many results I see.

Scope
The provided MySQL world database, specifically the city table containing city population data.

Level
Primary task, as this involves data analyst input, querying, and sorting data from the database.

Preconditions
*The SQL database is accessible and contains current city population data.
*The data analyst has the necessary permissions to query the database.

Success End Condition
A sorted list of the top N cities by population (largest to smallest) is generated and displayed with Name, Country Code, District, and Population columns.

Failed End Condition
No report is produced due to invalid data analyst input or database errors.

Primary Actor
Data analyst (the person who initiates the query to view the report).

Trigger
The data analyst requests a report to view the top N cities by population.

MAIN SUCCESS SCENARIO
Data analyst initiates a request to view top N cities.
*System prompts the user to enter a number N.
*Data analyst enters a positive integer.
*System validates the input.
*System queries the city table to retrieve the top N cities.
*System sorts the results by population in descending order.
*System displays the sorted list with Name, Country Code, District, and Population columns.
*Data analyst reviews the report.

EXTENSIONS
3a. Data analyst enters invalid input (non-number, negative, or zero):
*System displays an error message: "Please enter a valid positive number."
*System prompts the data analyst again.
4a. Database query fails (e.g., due to connectivity issues):
*System displays an error message to the data analyst, and the process ends.
5a. No city data available:
*System notifies the data analyst that no data was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0
