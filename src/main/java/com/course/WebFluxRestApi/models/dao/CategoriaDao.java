package com.course.WebFluxRestApi.models.dao;

import com.course.WebFluxRestApi.models.documents.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaDao extends ReactiveMongoRepository<Categoria, String> {
}
