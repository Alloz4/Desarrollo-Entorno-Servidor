package com.openwebinars.secondhandmarket_v02.respositorios;

import com.openwebinars.secondhandmarket_v02.modelo.Compra;
import com.openwebinars.secondhandmarket_v02.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByPropietario(Usuario propietario);
}
