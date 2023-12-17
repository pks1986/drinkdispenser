package com.example.drinkdispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.drinkdispenser.exception.DrinkDispenserException;
import com.example.drinkdispenser.service.DrinkDispenserService;

@SpringBootTest
class DrinkdispenserApplicationTests {

	@Test
	void contextLoads() {
	}
	
	private DrinkDispenserService drinkDispenserService;

    @BeforeEach
    void setUp() {
        drinkDispenserService = new DrinkDispenserService();
    }

    @Test
    void insertValidCoin() {
        String response = drinkDispenserService.insertCoin(50);
        assertEquals("Inserted 50 cents. Current Balance: 50 cents", response);
    }

    @Test
    void insertInvalidCoin() {
        assertThrows(DrinkDispenserException.class, () -> {
            drinkDispenserService.insertCoin(15);
        });
    }

    @Test
    void selectValidDrink() {
        drinkDispenserService.insertCoin(100); // Insert 1 dollar
        String response = drinkDispenserService.selectDrink("Coca");
        assertTrue(response.startsWith("Dispensing Coca"));
    }

    @Test
    void selectInvalidDrink() {
        assertThrows(DrinkDispenserException.class, () -> {
            drinkDispenserService.selectDrink("InvalidDrink");
        });
    }

    @Test
    void selectDrinkWithoutEnoughBalance() {
        assertThrows(DrinkDispenserException.class, () -> {
            drinkDispenserService.selectDrink("Coca");
        });
    }

    @Test
    void cancelOrderWithBalance() {
        drinkDispenserService.insertCoin(50); // Insert 50 cents
        String response = drinkDispenserService.cancelOrder();
        assertEquals("Order canceled. Returning 50 cents", response);
    }

    @Test
    void cancelOrderWithoutBalance() {
        assertThrows(DrinkDispenserException.class, () -> {
            drinkDispenserService.cancelOrder();
        });
    }

    @Test
    void displayStock() {
        String stock = drinkDispenserService.displayStock();
        assertNotNull(stock);
        assertTrue(stock.contains("Coca - $1.0"));
        // Add assertions for other drinks
    }

    @Test
    void displayStatus() {
        String status = drinkDispenserService.displayStatus();
        assertEquals("Current Balance: 0 cents", status);
    }
}