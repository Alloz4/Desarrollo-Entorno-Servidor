package com.openwebinars.secondhandmarket_v02.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openwebinars.secondhandmarket_v02.modelo.Compra;
import com.openwebinars.secondhandmarket_v02.modelo.Producto;
import com.openwebinars.secondhandmarket_v02.modelo.Usuario;
import com.openwebinars.secondhandmarket_v02.repositorios.CompraRepository;

@Service
public class CompraServicio {

    @Autowired
    CompraRepository repositorio;

    @Autowired
    ProductoServicio productoServicio;

    public Compra insertar(Compra c, Usuario u) {
        c.setPropietario(u);
        return repositorio.save(c);
    }

    public Compra insertar(Compra c) {
        return repositorio.save(c);
    }

    public Producto addProductoCompra(Producto p, Compra c) {
        p.setCompra(c);
        return productoServicio.editar(p);
    }

    public Compra buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Compra> todas() {
        return repositorio.findAll();
    }

    public List<Compra> porPropietario(Usuario u) {
        return repositorio.findByPropietario(u);
    }

}
