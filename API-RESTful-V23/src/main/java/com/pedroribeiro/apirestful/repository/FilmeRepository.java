package com.pedroribeiro.apirestful.repository;

import com.pedroribeiro.apirestful.model.Filme;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByCategoriaId(Long idCategoria);

    @Query("select p from Filme p " +
           "left outer join fetch p.categoria " +
           "order by p.id")
    List<Filme> recuperarFilmesComCategoria();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Filme p where p.id = :id")
    Optional<Filme> recuperarPorIdComLock(Long id);

    @Query(
            value = "select p from Filme p " +
                    "left outer join fetch p.categoria " +
                    "where p.nome like %:nome% " +
                    "order by p.id",
            countQuery = "select count(p) " +
                    "from Filme p " +
                    "where p.nome like %:nome% "
    )
    Page<Filme> recuperarFilmesComPaginacao(String nome, Pageable pageable);

    @Query("select p from Filme p " +
            "left outer join fetch p.categoria " +
            "where p.id = :id")
    Optional<Filme> recuperarFilmePorId(Long id);

    @Query("select p from Filme p left outer join fetch p.categoria c where c.slug = :slug")
    List<Filme> findByCategoriaSlug(String slug);

    @Query(
            value = "select p from Filme p " +
                    "left outer join fetch p.categoria c " +
                    "where c.slug = :slug " +
                    "order by p.nome",
            countQuery = "select count(p) " +
                    "from Filme p " +
                    "left outer join p.categoria c " +
                    "where c.slug = :slug  "
    )
    Page<Filme> recuperarFilmesPaginadosPorSlugDaCategoria(String slug, Pageable pageable);

    @Query(
            value = "select p from Filme p " +
                    "left outer join fetch p.categoria c " +
                    "order by p.nome",
            countQuery = "select count(p) from Filme p "
    )
    Page<Filme> recuperarFilmesPaginados(Pageable pageable);
}
