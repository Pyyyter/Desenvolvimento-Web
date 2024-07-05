package com.pedroribeiro.apirestful.repository;

import com.pedroribeiro.apirestful.model.Categoria;
import com.pedroribeiro.apirestful.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c left outer join fetch c.filmes where c.id = 1")
    Optional<Categoria> recuperarCategoriaComFilmesPorIdDaCategoria(long id);
}
