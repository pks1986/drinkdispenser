package com.example.drinkdispenser.controller;

import com.example.drinkdispenser.exception.DrinkDispenserException;
import com.example.drinkdispenser.service.DrinkDispenserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drinkdispenser")
public class DrinkDispenserController {

    private final DrinkDispenserService drinkDispenserService;

    @Autowired
    public DrinkDispenserController(DrinkDispenserService drinkDispenserService) {
        this.drinkDispenserService = drinkDispenserService;
    }

    @PostMapping("/insertCoin/{coinValue}")
    public ResponseEntity<String> insertCoin(@PathVariable int coinValue) {
    	String response = drinkDispenserService.insertCoin(coinValue);
        logRequest("POST", "/insertCoin/" + coinValue);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/selectDrink/{drinkCode}")
    public ResponseEntity<String> selectDrink(@PathVariable String drinkCode) {
    	String response = drinkDispenserService.selectDrink(drinkCode);
        logRequest("POST", "/selectDrink/" + drinkCode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancelOrder")
    public ResponseEntity<String> cancelOrder() {
        try {
            String response = drinkDispenserService.cancelOrder();
            logRequest("GET", "/cancelOrder");
            return ResponseEntity.ok(response);
        } catch (DrinkDispenserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/displayStock")
    public String displayStock() {
        logRequest("GET", "/displayStock");
        return drinkDispenserService.displayStock();
    }

    @GetMapping("/displayStatus")
    public String displayStatus() {
        logRequest("GET", "/displayStatus");
        return drinkDispenserService.displayStatus();
    }

    private void logRequest(String method, String endpoint) {
        System.out.println("Received request: " + method + " /api/drinkdispenser" + endpoint);
    }
}