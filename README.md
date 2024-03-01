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
   Send a GET request to `http://localhost:8080/api/banks/{externalApiName}/currencies` with the name of the external API as a parameter and you will receive a JSON response with an array of currency codes (ISO 4217).
   For example, `http://localhost:8080/api/banks/ALFA_BANK/currencies`.
3. **Get the exchange rate of a specific currency from a specific external API for a specific date**.
   Send a GET request to `http://localhost:8080/rates` with the name of the external API, the currency code (ISO 4217), and the date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates?externalApiName=ALFA_BANK&currencyCode=EUR&date=2024-02-13`.
   You will receive a JSON response with an object containing the sell and buy rates.
4. **Get the exchange rates of a specific currency from a specific external API for a specific period**.
   Send a GET request to `http://localhost:8080/rates` with the name of the external API, the currency code (ISO 4217), the start date, and the end date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates?externalApiName=ALFA_BANK&currencyCode=EUR&startDate=2024-01-01&endDate=2024-01-15`.
   You will receive a JSON response with an object containing the sell and buy rates for that day.
5. **Get statistics on the exchange rates of a specific currency from a specific external API for a specific period**.
   Send a GET request to `http://localhost:8080/rates/statistics` with the name of the external API, the currency code (ISO 4217), the start date, and the end date in YYYY-MM-DD format as parameters.
   For example, `http://localhost:8080/rates/statistics?externalApiName=ALFA_BANK&currencyCode=EUR&startDate=2024-01-01&endDate=2024-01-15`.
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

### Specific steps

To launch a Telegram bot, you need to know its name and token. This data is stored in **application.yml**. To install them, you must create the following environment variables: `BOT_NAME` and `BOT_TOKEN`. These environment variables will be used by the application to identify and authorize the bot to the Telegram API. Make sure they are set correctly before launching the bot.

# Telegram Bot for Currency Exchange Rates

This Telegram Bot provides users with information about currency exchange rates. It is written in Java using Spring Boot and interacts with users through the Telegram API.

## How to Use the Bot

1. Getting Started:
   - The user starts the bot with the command `/start`.

2. Selecting a Bank:
   - The bot offers the user to select a bank from the available list. The user selects a bank by pressing the corresponding button.

3. Selecting a Currency:
   - After choosing a bank, the bot offers the user to select a currency from the available list. The user selects a currency by pressing the corresponding button.
   - During the currency selection process, the user can also return to the bank selection by pressing a designated button.

4. Choosing a Function:
   - After selecting the currency, the bot offers the user to choose one of three functions:
      1. Getting the currency exchange rate for today.
      2. Getting the currency exchange rate for a selected date.
      3. Getting the currency exchange rate statistics for a period of time.
   - During the function selection process, the user can return to either bank selection or currency selection by pressing designated buttons.

## Bot Functions

1. Getting the Currency Exchange Rate for Today:
   - The bot displays the current exchange rate of the selected currency to the user.

2. Getting the Currency Exchange Rate for a Selected Date:
   - The bot requests the user to input the date for which the currency exchange rate is needed.
   - The user inputs the date in the format `YYYY-MM-DD`.
   - The bot displays the exchange rate of the selected currency for the specified date to the user.

3. Getting the Currency Exchange Rate Statistics for a Period of Time:
   - The bot requests the user to input the time period for which the currency exchange rate statistics are needed.
   - The user inputs the start and end dates of the period in the format `YYYY-MM-DD`.
   - The bot generates a graph showing the fluctuation of the exchange rate of the selected currency over the specified period and sends it to the user.

## Technical Details

- Spring Boot framework is used to create the bot.
- Interaction with the Telegram API is facilitated through the `telegrambots` library.
- Currency exchange rates are retrieved from the bank's API (e.g., National Bank API).
- Charts for statistics are generated using a data visualization library (e.g., JFreeChart).

## How to Set Up and Run the Bot

1. Create a bot on Telegram using `BotFather`.
2. Once the bot is created, you will receive a bot username and token from `BotFather`. Take note of these credentials.
3. Set up the following environment variables:
   - `BOT_NAME`: Your bot's username obtained from `BotFather`.
   - `BOT_TOKEN`: The token provided by `BotFather` for your bot.
4. Configure access to the bank's API for retrieving currency exchange rates.
5. Run the application.
