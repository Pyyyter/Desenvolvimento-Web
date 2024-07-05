package com.pedroribeiro.apirestful.service;

import com.pedroribeiro.apirestful.exception.EntidadeDestacadaException;
import com.pedroribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.pedroribeiro.apirestful.model.Categoria;
import com.pedroribeiro.apirestful.model.CategoriaDTO;
import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.repository.CategoriaRepository;
import com.pedroribeiro.apirestful.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FilmeRepository filmeRepository;

    public Optional<Categoria> recuperarCategoria(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public Categoria recuperarCategoriaComFilmes(Long idCategoria) {
        return categoriaRepository.recuperarCategoriaComFilmesPorIdDaCategoria(idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + idCategoria + " não encontrada"));
    }

    public List<Categoria> recuperarCategorias() {
        return categoriaRepository.findAll();
    }
}
