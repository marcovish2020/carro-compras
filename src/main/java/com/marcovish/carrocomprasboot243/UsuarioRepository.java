package com.marcovish.carrocomprasboot243;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Query("UPDATE Usuario u SET u.email = :email, u.password = :password WHERE u.id = :id")
    void updateUsuario(@Param("email") String email, @Param("password") String password, @Param("id") Long id);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario selectUsuario(@Param("email") String email, @Param("password") String password);
}
