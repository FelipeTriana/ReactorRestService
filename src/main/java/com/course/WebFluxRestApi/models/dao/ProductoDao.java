package com.course.WebFluxRestApi.models.dao;

import com.course.WebFluxRestApi.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String> {


}
