package com.example.drinkdispenser.controller;

import com.example.drinkdispenser.service.DrinkDispenserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String insertCoin(@PathVariable int coinValue) {
        String response = drinkDispenserService.insertCoin(coinValue);
        System.out.println("Received request: POST /api/drinkdispenser/insertCoin/" + coinValue);
        System.out.println("Response: " + response);
        return response;
    }

    @PostMapping("/selectDrink/{drinkCode}")
    public String selectDrink(@PathVariable String drinkCode) {
        String response = drinkDispenserService.selectDrink(drinkCode);
        System.out.println("Received request: POST /api/drinkdispenser/selectDrink/" + drinkCode);
        System.out.println("Response: " + response);
        return response;
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder() {
        String response = drinkDispenserService.cancelOrder();
        System.out.println("Received request: GET /api/drinkdispenser/cancelOrder");
        System.out.println("Response: " + response);
        return response;
    }

    @GetMapping("/displayStock")
    public String displayStock() {
        String response = drinkDispenserService.displayStock();
        System.out.println("Received request: GET /api/drinkdispenser/displayStock");
        System.out.println("Response: " + response);
        return response;
    }

    @GetMapping("/displayStatus")
    public String displayStatus() {
        String response = drinkDispenserService.displayStatus();
        System.out.println("Received request: GET /api/drinkdispenser/displayStatus");
        System.out.println("Response: " + response);
        return response;
    }
}

