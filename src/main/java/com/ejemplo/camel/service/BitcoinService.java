//Aca organizamos de pana todos los Beans de procesamiento uwu
package com.ejemplo.camel.service;

import org.springframework.stereotype.Component;

import com.ejemplo.camel.model.PrecioBitcoin;

@Component // Registra el Bean en Spring pa que camel pueda usarlo
public class BitcoinService {

	// Metodo que sera llamado desde la ruta camel con .bean
	public void procesar(PrecioBitcoin precio) {
		System.out.println("Procesando precios de Bitcoin: " + precio);
	}
}
