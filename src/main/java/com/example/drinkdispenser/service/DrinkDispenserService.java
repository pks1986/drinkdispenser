package com.example.drinkdispenser.service;

import com.example.drinkdispenser.exception.DrinkDispenserException;
import com.example.drinkdispenser.model.Coin;
import com.example.drinkdispenser.model.Drink;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DrinkDispenserService {

	private final Map<Integer, Coin> acceptedCoins;
	private final Map<String, Drink> availableDrinks;
	private int currentBalance;

	public DrinkDispenserService() {
		acceptedCoins = initializeAcceptedCoins();
		availableDrinks = initializeAvailableDrinks();
		currentBalance = 0;
	}

	private Map<Integer, Coin> initializeAcceptedCoins() {
		Map<Integer, Coin> coins = new HashMap<>();
		coins.put(5, new Coin(5));
		coins.put(10, new Coin(10));
		coins.put(20, new Coin(20));
		coins.put(50, new Coin(50));
		coins.put(100, new Coin(100));
		coins.put(200, new Coin(200));
		return coins;
	}

	private Map<String, Drink> initializeAvailableDrinks() {
		Map<String, Drink> drinks = new HashMap<>();
		drinks.put("Coca", new Drink("Coca", 100));
		drinks.put("Redbull", new Drink("Redbull", 125));
		drinks.put("Water", new Drink("Water", 50));
		drinks.put("Orange Juice", new Drink("Orange Juice", 195));
		return drinks;
	}

	public String insertCoin(int coinValue) {
		Coin coin = acceptedCoins.get(coinValue);
		if (coin != null) {
			currentBalance += coin.getValue();
			return "Inserted " + coinValue + " cents. Current Balance: " + currentBalance + " cents";
		} else {
			throw new DrinkDispenserException("Invalid coin. Please insert a valid coin.");
		}
	}

	public String selectDrink(String drinkCode) {
		Drink selectedDrink = availableDrinks.get(drinkCode);

		if (selectedDrink == null) {
			throw new DrinkDispenserException("Invalid drink code. Please select a valid drink.");
		}

		int drinkPrice = selectedDrink.getPrice();

		if (currentBalance < drinkPrice) {
			throw new DrinkDispenserException(
					"Insufficient balance. Please insert more coins to purchase " + selectedDrink.getName());
		}

		// Dispense the drink
		String response = "Dispensing " + selectedDrink.getName();

		// Calculate and return change
		int change = currentBalance - drinkPrice;
		if (change > 0) {
			response += ". Change: " + change + " cents";
		}

		// Reset current balance
		currentBalance = 0;

		return response;
	}

	public String cancelOrder() {
		if (currentBalance > 0) {
			String response = "Order canceled. Returning " + currentBalance + " cents";
			currentBalance = 0;
			return response;
		} else {
			throw new DrinkDispenserException("No order to cancel. Current balance is zero.");
		}
	}

	public String displayStock() {
		StringBuilder response = new StringBuilder("Available Drinks:\n");
		for (Map.Entry<String, Drink> entry : availableDrinks.entrySet()) {
			response.append(entry.getKey()).append(" - $").append(entry.getValue().getPrice() / 100.0).append("\n");
		}
		return response.toString();
	}

	public String displayStatus() {
		return "Current Balance: " + currentBalance + " cents";
	}
}