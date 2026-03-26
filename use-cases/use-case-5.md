Use Case 5: View Population Breakdown by Continent

CHARACTERISTIC INFORMATION

Goal in Context
As a data analyst, I want to view the population breakdown by continent including total population, population living in cities (with percentage), and population not living in cities (with percentage), so that I can understand urbanization rates across continents.

Scope
The provided MySQL world database, specifically the country and city tables containing population data.

Level
Primary task, as this involves complex queries with subqueries and calculations.

Preconditions
*The SQL database is accessible and contains current country and city population data.
*The data analyst has the necessary permissions to query the database.

Success End Condition
A population breakdown by continent is generated and displayed with Continent, Total Population, City Population (with %), and Non-City Population (with %) for all continents.

Failed End Condition
No report is produced due to database errors or lack of data.

Primary Actor
Data analyst (the person who initiates the query to view the report).

Trigger
The data analyst requests a report to view the population breakdown by continent.

MAIN SUCCESS SCENARIO
Data analyst initiates a request to view population breakdown by continent.
*System calculates total population per continent from the country table.
*System calculates city population per continent by summing city populations grouped by country.
*System calculates non-city population (total population minus city population).
*System calculates percentages for city and non-city populations.
*System sorts results by total population descending.
*System displays the breakdown with Continent, Total Population, City Population (%), and Non-City Population (%) columns.
*Data analyst reviews the report to understand urbanization rates.

EXTENSIONS
2a. Database query fails (e.g., due to connectivity issues):
*System displays an error message to the data analyst, and the process ends.
3a. No population data available:
*System notifies the data analyst that no data was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0

