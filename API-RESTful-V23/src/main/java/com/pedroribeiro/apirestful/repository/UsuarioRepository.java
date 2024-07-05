package com.pedroribeiro.apirestful.repository;

import com.pedroribeiro.apirestful.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByContaAndSenha(String conta, String senha);
}
