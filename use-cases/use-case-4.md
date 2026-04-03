Use Case 4: View Capital Cities by Continent

CHARACTERISTIC INFORMATION

Goal in Context
As a data analyst, I want to enter a continent and view all capital cities in that continent organized by largest population to smallest, so that I can compare capital city sizes across continents.

Scope
The provided MySQL world database, specifically the city and country tables containing capital city and continent data.

Level
Primary task, as this involves user input, joining tables, querying, and sorting data from the database.

Preconditions
The SQL database is accessible and contains current capital city and continent data.
The data analyst has the necessary permissions to query the database.

Success End Condition
A sorted list of capital cities in the selected continent by population (largest to smallest) is generated and displayed with Name, Country Code, and Population columns.

Failed End Condition
No report is produced due to invalid continent input, database errors, or lack of data.

Primary Actor
Data analyst (the person who initiates the query to view the report).

Trigger
The data analyst requests a report to view capital cities by continent.

MAIN SUCCESS SCENARIO
Data analyst initiates a request to view capital cities by continent.
*System prompts the data analyst to enter a continent name.
*Data analyst enters a continent (e.g., Asia, Europe, Africa, North America, South America, Australia, Antarctica).
*System validates the continent input (case-insensitive).
*System joins the city and country tables to find capital cities.
*System filters results by the selected continent.
*System sorts the results by population in descending order.
*System displays the sorted list with Name, Country Code, and Population columns.
*Data analyst reviews the report.

EXTENSIONS
3a. Data analyst enters an invalid continent:
*System displays an error message: "Invalid continent. Try: Asia, Europe, Africa, North America, South America, Australia, Antarctica."
*System prompts the data analyst again.
4a. Database query fails (e.g., due to connectivity issues):
*System displays an error message to the data analyst, and the process ends.
5a. No capital cities found for the continent:
*System notifies the data analyst that no capital cities were found for that continent.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0
