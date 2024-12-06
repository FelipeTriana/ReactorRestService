package com.course.WebFluxRestApi;

import com.course.WebFluxRestApi.models.documents.Categoria;
import com.course.WebFluxRestApi.models.documents.Producto;
import com.course.WebFluxRestApi.models.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class WebFluxRestApiApplication implements CommandLineRunner {

	@Autowired
	private ProductoService service;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(WebFluxRestApiApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(WebFluxRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("productos").subscribe();
		mongoTemplate.dropCollection("categorias").subscribe();

		Categoria electronico = new Categoria("Electrónico");
		Categoria deporte = new Categoria("Deporte");
		Categoria computacion = new Categoria("Computación");
		Categoria muebles = new Categoria("Mueble");

		Flux.just(electronico, deporte, computacion, muebles)
				.flatMap(service::saveCategoria)
				.doOnNext(c -> log.info("Categoria creada: " + c.getId() + " " + c.getNombre()))
				.thenMany(  //thenMany permite iniciar la ejecucion de un flujo Flux despues de la ejecucion del flujo de categorias
						Flux.just(new Producto("Televisor", 456.89, electronico),
										new Producto("Radio", 89.00, electronico),
										new Producto("Laptop", 789.12, computacion),
										new Producto("Tablet", 450.00, electronico),
										new Producto("Impresora", 300.00, electronico),
										new Producto("Celular", 150.00, electronico),
										new Producto("Mica Shidori", 150.00, muebles),
										new Producto("Silla Gamer", 150.00, muebles),
										new Producto("Mesa Gamer", 150.00, muebles),
										new Producto("Gimnasio", 150.00, deporte),
										new Producto("Cinta de correr", 150.00, deporte)

								)
								.flatMap(producto -> {
									producto.setCreateAt(new Date());
									return service.save(producto);
								})
				)
				.subscribe(prod -> log.info("Insert: " + prod.getId() + " " + prod.getNombre()));
	}
}
