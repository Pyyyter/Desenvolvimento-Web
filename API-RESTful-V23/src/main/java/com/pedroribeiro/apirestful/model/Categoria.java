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
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String slug;
    @JsonIgnore
    @OneToMany (mappedBy = "categoria")
    private List<Filme> filmes;

    public Categoria(String nome, String slug) {
        this.nome = nome;
        this.slug = slug;
        this.filmes = new ArrayList<>();
    }
}
