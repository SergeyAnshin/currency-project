# Currency Project

## Description

This project is a REST application that uses Spring Boot and Java to get exchange rates from different external sources.
You can choose external source, the currency, and the date or the period. 
The project also provides statistics on the exchange rates, such as average, minimum, maximum etc.

### Features

This project allows you to:

1. **Get a list of all available external APIs** that provide exchange rates data.
   Just send a GET request to `http://localhost:8080/apis` and you will receive a JSON response with an array containing the name of an external API.
2. **Get a list of all available currencies for a given external API**.
   Send a GET request to `http://localhost:8080/currencies` with the name of the external API as a parameter and you will receive a JSON response with an array of currency codes (ISO 4217).
3. **Get the exchange rate of a specific currency from a specific external API for a specific date**.
   Send a GET request to `http://localhost:8080/rates` with the name of the external API, the currency code (ISO 4217), and the date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates?externalApiName=ALFA_BANK&currency=EUR&date=2024-02-13`.
   You will receive a JSON response with an object containing the sell and buy rates.
4. **Get the exchange rates of a specific currency from a specific external API for a specific period**.
   Send a GET request to `http://localhost:8080/rates` with the name of the external API, the currency code (ISO 4217), the start date, and the end date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates/period?externalApiName=ALFA_BANK&currency=EUR&startDate=2024-01-01&endDate=2024-01-15`.
   You will receive a JSON response with an object containing the sell and buy rates for that day.
5. **Get statistics on the exchange rates of a specific currency from a specific external API for a specific period**.
   Send a GET request to `http://localhost:8080/rates/statistics` with the name of the external API, the currency code (ISO 4217), the start date, and the end date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates/statistics?externalApiName=ALFA_BANK&currency=EUR&startDate=2024-01-01&endDate=2024-01-15`.
   You will receive a JSON response with an object containing the base currency, the target currency, the period, and an object with statistics, such as average, minimum, maximum, etc.

## Installation

### Requirements

- Java 17
- Maven

### How to run

To start the project you need:

1. Install Java 17 or higher 
2. Install and set up Maven
3. Clone a repository from GitHub
   ```bash
   git clone https://github.com/SergeyAnshin/currency-project.git
   ```
4. Go to the project folder and run the command `mvn spring-boot:run`
5. Open a browser and go to `http://localhost:8080`
