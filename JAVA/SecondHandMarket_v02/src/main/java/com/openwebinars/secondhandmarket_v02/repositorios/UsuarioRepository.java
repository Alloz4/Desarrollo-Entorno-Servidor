package com.openwebinars.secondhandmarket_v02.repositorios;

import com.openwebinars.secondhandmarket_v02.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByEmail(String email);

}
