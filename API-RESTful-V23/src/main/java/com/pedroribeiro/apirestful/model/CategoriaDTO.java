package com.pedroribeiro.apirestful.model;

import java.util.List;

public record CategoriaDTO(Long id, String nome, List<Filme> lista) {

}
