package com.ejemplo.camel.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockBitcoinController {

    @GetMapping("/mock/bitcoin")
    public Map<String, Object> getBitcoin() {
    	return Map.of(
    	        "id", "bitcoin",
    	        "symbol", "BTC",
    	        "priceUsd", "27230.12",
    	        "timestamp", System.currentTimeMillis()
    	    );
    }
}