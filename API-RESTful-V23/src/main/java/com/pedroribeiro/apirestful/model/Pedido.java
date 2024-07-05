package com.pedroribeiro.apirestful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    private Long userID;

    public Pedido(Long userID, Filme filme) {
        this.userID = userID;
        this.filme = filme;
    }
}
