//En el package Route se organizan todas las rutas Camel

package com.ejemplo.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.ejemplo.camel.aggregation.ArrayListAggregationStrategy;

@Component // Registra la ruta en Spring Boot (El import es stereotype)

// el extends RouteBuilder nos permite definir rutas Camel :)
public class BitcoinRoute extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		
		//Ruta inicial usando timer
		from("timer://bitcoinTimer?period=10000") // sale cada 10 segs
			.log("Iniciando consumo de la API local")
			//Consumimos la api
			.to("http://localhost:8080/mock/bitcoin") // .to envia request y nos manda response
			.log("Respuesta de la API: ${body}")
			//Convertimos JSON a objeto Java
			.unmarshal().json(org.apache.camel.model.dataformat.JsonLibrary.Jackson
				    	,com.ejemplo.camel.model.PrecioBitcoin.class)
			.log("Objeto tuneado: ${body}")
			// Ahora el ${body} contiene el objeto PrecioBitcoin, no el JSON crudo
		
			// Pasamos el obj o pojo al Bean para procesarlo
			.bean(com.ejemplo.camel.service.BitcoinService.class, "procesar")
			//envia una copia a auditoria
			.wireTap("direct:auditoria");

		//ruta de auditoria
		from("direct:auditoria")
			.log("Auditoria - Copia del mensaje: ${body}")
			
			// con el marshall convertimos un objeto java a JSON o XML, al contrario de unmarshall
			.marshal().json(org.apache.camel.model.dataformat.JsonLibrary.Jackson)
			.log("Objeto convertido a JSON: ${body}")
			
			//Convertimos el body a string, o cualquier otro tipo
			.convertBodyTo(String.class)
			.log("Precio en String: ${body}")
			
			
			//Con el constant enviamos un valor constante a un endpoint como header
			.setHeader("moneda", constant("USD"))
			.log("Header moneda: ${header.moneda}")
			
			//en el aggregate combinamos varios mensajes en 1
			.aggregate(constant(true), new ArrayListAggregationStrategy())
			.completionSize(5) // aca espera 5 mensajes antes de procesar
			.log("Mensajes agregados ${body}");
		
	}
	
}
