package com.ejemplo.camel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//El data genera los getters, setters, toString equals, y hashCode
@Data
//El NoArgsConstructor genera un constructor vacio, necesario para jacksonear
@NoArgsConstructor
//AllArgsConstructor crea el constructor con todos los campos
@AllArgsConstructor

public class PrecioBitcoin {

	private String id;
	private String symbol;
	private String priceUsd;
	private long timestamp;
    
}
