Use Case 1: View All Countries by Population

CHARACTERISTIC INFORMATION

Goal in Context
As data analyst, I want to view all countries in the world organized by largest population to smallest, so that I can understand global population distribution.

Scope
The provided MySQL world database, specifically the country table containing country population data.

Level
Primary task, as this involves querying and sorting data from the database.

Preconditions
*The SQL database is accessible and contains current country population data.
*The data analyst has the necessary permissions to query the database.

Success End Condition
A sorted list of all countries by population (largest to smallest) is generated and displayed with Code, Name, Continent, Region, Population, and Capital columns.

Failed End Condition
No report is produced, such as due to database errors or lack of data.

Primary Actor
Data Analyst (the person who initiates the query to view the report).

Trigger
The data analyst requests a report to view all countries ordered by population.

MAIN SUCCESS SCENARIO
Data analyst initiates a request to view countries by population.
*System queries the country table to retrieve all countries.
*System sorts the results by population in descending order.
*System displays the sorted list with Code, Name, Continent, Region, Population, and Capital columns.
*User reviews the report to understand population distribution globally.

EXTENSIONS
2a. Database query fails (e.g., due to connectivity issues):
*System displays an error message to the data analyst, and the process ends.
3a. No country data available:
*System notifies the data analyst that no data was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0
