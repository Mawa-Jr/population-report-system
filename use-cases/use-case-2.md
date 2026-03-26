Use Case 2: View All Cities by Population

CHARACTERISTIC INFORMATION

Goal in Context
As a data analyst, I want to view all cities in the world organized by largest population to smallest, so that I can identify the most populated cities globally.

Scope
The provided MySQL world database, specifically the city table containing city population data.

Level
Primary task, as this involves querying and sorting data from the database.

Preconditions
*The SQL database is accessible and contains current city population data.
*The data analyst has the necessary permissions to query the database.

Success End Condition
A sorted list of all cities by population (largest to smallest) is generated and displayed with Name, Country Code, District, and Population columns.

Failed End Condition
No report is produced, such as due to database errors or lack of data.

Primary Actor
Data analyst (the person who initiates the query to view the report).

Trigger
The data analyst requests a report to view all cities ordered by population.

MAIN SUCCESS SCENARIO

Data analyst initiates a request to view cities by population.
*System queries the city table to retrieve all cities.
*System sorts the results by population in descending order.
*System displays the sorted list with Name, Country Code, District, and Population columns.
*User reviews the report to identify the most populated cities.

EXTENSIONS
2a. Database query fails (e.g., due to connectivity issues):
*System displays an error message to the data analyst, and the process ends.
3a. No city data available:
*System notifies the data analyst that no data was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0
