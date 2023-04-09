package com.openwebinars.secondhandmarket_v02.respositorios;

import com.openwebinars.secondhandmarket_v02.modelo.Compra;
import com.openwebinars.secondhandmarket_v02.modelo.Producto;
import com.openwebinars.secondhandmarket_v02.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByPropietario(Usuario propietario);

    List<Producto> findByCompra(Compra compra);

    List<Producto> findByCompraIsNull();

    List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);

    List<Producto> findByNombreContainsIgnoreCaseAndPropietario(String nombre, Usuario propietario);

}
