package com.example.drinkdispenser.service;

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
        acceptedCoins = new HashMap<>();
        availableDrinks = new HashMap<>();
        currentBalance = 0;
        initializeCoins();
        initializeDrinks();
    }

    private void initializeCoins() {
        acceptedCoins.put(5, new Coin(5));
        acceptedCoins.put(10, new Coin(10));
        acceptedCoins.put(20, new Coin(20));
        acceptedCoins.put(50, new Coin(50));
        acceptedCoins.put(100, new Coin(100));
        acceptedCoins.put(200, new Coin(200));
    }

    private void initializeDrinks() {
        availableDrinks.put("Coca", new Drink("Coca", 100));
        availableDrinks.put("Redbull", new Drink("Redbull", 125));
        availableDrinks.put("Water", new Drink("Water", 50));
        availableDrinks.put("Orange Juice", new Drink("Orange Juice", 195));
    }

    public String insertCoin(int coinValue) {
        Coin coin = acceptedCoins.get(coinValue);
        if (coin != null) {
            currentBalance += coin.getValue();
            return "Inserted " + coinValue + " cents. Current Balance: " + currentBalance + " cents";
        } else {
            throw new IllegalArgumentException("Invalid coin. Please insert a valid coin.");
        }
    }

    public String selectDrink(String drinkCode) {
        Drink selectedDrink = availableDrinks.get(drinkCode);

        if (selectedDrink == null) {
            throw new IllegalArgumentException("Invalid drink code. Please select a valid drink.");
        }

        int drinkPrice = selectedDrink.getPrice();

        if (currentBalance < drinkPrice) {
            throw new IllegalStateException("Insufficient balance. Please insert more coins.");
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
        String response = "Order canceled. Returning " + currentBalance + " cents";
        currentBalance = 0;
        return response;
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
