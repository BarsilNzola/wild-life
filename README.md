## Wildlife Tracker
This program was created using Java language.

## Author
Barsil Odiwuor Ochola.

### Description
an application that allows Rangers to track wildlife sightings in an area

## Prerequisites & setup
### Prerequisites
- Java Spark 
- PostgreSQL
- SQL
- JUnit-Unit Testing
- Git and Github- Version control
- Heroku

### Set up
- Clone the project open it in your favourite IDE favourably IntelliJ.
- Set Up database
 
1.   ```CREATE DATABASE wildlife_tracker;```
2. ```CREATE TABLE sightings(id serial PRIMARY KEY, animalId INTEGER,location VARCHAR, rangerName VARCHAR);```
3. ```CREATE TABLE animals(id serial PRIMARY KEY,name VARCHAR, nickname VARCHAR, species VARCHAR, type VARCHAR);```
4. ```CREATE TABLE rangers(id SERIAL PRIMARY KEY, name VARCHAR, number VARCHAR, badgeNumber INTEGER);```

- Run `gradle build` to build the project and install the dependencies. The build artifacts will be stored in the `build/` directory.
- Hit the `run` button to continue .
- Navigate to `http://localhost:4567/` in your browser.

## BDD
| Behaviour       | Input               | Output              |
| --------------- |:-------------------:| ------------------: |
| loads page      | click GET STARTED   | sighting form       |
| click submit    | click submit  button| sighting list       |
| loads page      | click RANGERS       | ranger form         |
| click submit    | click submit button | rangers list        |

## Known Bugs
In case of of any bugs found please contact using the information below.

## Contact Information
email - barsiljohn@gmail.com
call - +254 707159461

## Copyright and license.
(c) 2020 Barsil Odiwuor Ochola.
MIT License