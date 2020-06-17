package com.acelera.java.hotel.repository;

import com.acelera.java.hotel.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
}
