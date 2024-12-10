package com.course.WebFluxRestApi;

import com.course.WebFluxRestApi.handler.ProductoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


//Contiene las rutas de nuestros componentes Handler que se encargan de manejar las peticiones al api rest
@Configuration
public class RouterFunctionConfig {

    /**
     * Rutas de nuestro api rest
     * @return
     * Este Bean retornara un RouterFunction de tipo Server Response que contendra las rutas de nuestro api rest
     */
    @Bean //Similar a @Component para registrar un componente en el contenedor de Spring pero este registra via metodo
    public RouterFunction<ServerResponse> routes(ProductoHandler handler) {
        return RouterFunctions.route(GET( "/api/v2/productos"), handler::listar)
                .andRoute(GET("/api/v2/productos/{id}"), handler::ver)
                .andRoute(POST("/api/v2/productos"), handler::crear)
                .andRoute(PUT("/api/v2/productos/{id}"), handler::editar)
                .andRoute(DELETE("/api/v2/productos/{id}"), handler::eliminar)
                .andRoute(POST("/api/v2/productos/upload/{id}"), handler::upload)
                .andRoute(POST("/api/v2/productos/crear-con-foto"), handler::crearConFoto);
    }


}
