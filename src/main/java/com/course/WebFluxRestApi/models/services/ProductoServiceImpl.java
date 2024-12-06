package com.course.WebFluxRestApi.models.services;

import com.course.WebFluxRestApi.models.dao.CategoriaDao;
import com.course.WebFluxRestApi.models.dao.ProductoDao;
import com.course.WebFluxRestApi.models.documents.Categoria;
import com.course.WebFluxRestApi.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService {

    /*Recordar: En Spring para poder inyectar con Autowired la clase donde se inyecta y la clase que se inyecta deben
      estar anotadas como componentes (O heredar de un componente como es el caso de ProductoDao)*/
    @Autowired
    private ProductoDao dao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public Flux<Producto> findAll() {
        return dao.findAll();
    }

    @Override
    public Flux<Producto> findAllConNombreUpperCase() {
        return dao.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        });
    }

    @Override
    public Flux<Producto> findAllConNombreUpperCaseRepeat() {
        return findAllConNombreUpperCase().repeat(5000);
    }

    @Override
    public Mono<Producto> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return dao.delete(producto);
    }

    @Override
    public Flux<Categoria> findAllCategoria() {
        return categoriaDao.findAll();
    }

    @Override
    public Mono<Categoria> findCategoriaById(String id) {
        return categoriaDao.findById(id);
    }

    @Override
    public Mono<Categoria> saveCategoria(Categoria categoria) {
        return categoriaDao.save(categoria);
    }
}
