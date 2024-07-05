package com.pedroribeiro.apirestful.repository;

import com.pedroribeiro.apirestful.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p where p.userID = :userId")
    List<Pedido> findByUserId(@Param("userId") Long userId);
}
