package com.pedroribeiro.apirestful.controller;

import com.pedroribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.pedroribeiro.apirestful.model.Categoria;
import com.pedroribeiro.apirestful.model.CategoriaDTO;
import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping   // http://localhost:8080/filmes
    public List<Categoria> recuperarCategorias() {
        return categoriaService.recuperarCategorias();
    }

    @GetMapping("{idCategoria}")
    public Categoria recuperarCategoria(@PathVariable("idCategoria") Long idCategoria) {
        return categoriaService.recuperarCategoria(idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + idCategoria + " não encontrada"));
    }

    @GetMapping("{idCategoria}/filmes")          // http://localhost:8080/categorias/1/filmes
    public CategoriaDTO recuperarCategoriaComFilmes(@PathVariable("idCategoria") Long idCategoria) {
        Categoria categoria = categoriaService.recuperarCategoriaComFilmes(idCategoria);
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getFilmes());
    }
}
