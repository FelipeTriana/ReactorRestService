package com.course.WebFluxRestApi;

import com.course.WebFluxRestApi.models.documents.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebFluxRestApiApplicationTests {

	@Autowired
	private WebTestClient client;

	@Test
	void contextLoads() {
	}

	@Test
	void listarTest(){
		client.get().uri("/api/v2/productos")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType("application/json")
				.expectBodyList(Producto.class)
				.consumeWith(response -> {
					List<Producto> productos = response.getResponseBody();
					productos.forEach(p -> {
						System.out.println(p.getNombre());
					});
					Assertions.assertTrue(productos.size() > 0);
				});
	}

}
