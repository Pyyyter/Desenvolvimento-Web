package com.pedroribeiro.apirestful.controller;

import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.model.ResultadoPaginado;
import com.pedroribeiro.apirestful.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping   // http://localhost:8080/filmes
    public List<Filme> recuperarFilmes() {
        return filmeService.recuperarFilmes();
    }

    @PostMapping   // http://localhost:8080/filmes
    public Filme cadastrarFilme(@RequestBody Filme filme) {
        return filmeService.cadastrarFilme(filme);
    }

    @PutMapping
    public ResponseEntity<Filme> alterarFilme(@RequestBody Filme filme) {
        Filme umFilme = filmeService.alterarFilme(filme);
        return new ResponseEntity<Filme>(umFilme,HttpStatus.OK);
    }

    @DeleteMapping ("{idFilme}")     // http://localhost:8080/filmes/1
    public Filme removerFilme(@PathVariable("idFilme") Long id) {
        return filmeService.removerFilme(id);
    }

    @GetMapping("categoria/{idCategoria}")             // http://localhost:8080/filmes/categoria/1
    public List<Filme> recuperarFilmesPorIdDaCategoria(@PathVariable("idCategoria") Long idCategoria) {
        System.out.println(idCategoria);
        return filmeService.recuperarFilmesPorIdDaCategoria(idCategoria);
    }

    @GetMapping("categorias")    // http://localhost:8080/filmes/categorias
    public List<Filme> recuperarFilmesComCategoria() {
        return filmeService.recuperarFilmesComCategoria();
    }

    // http://localhost:8080/filmes/paginacao?pagina=0&tamanho=5
    @GetMapping("paginacao")
    public ResultadoPaginado<Filme> recuperarFilmesComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Filme> page = filmeService.recuperarFilmesComPaginacao(nome, pageable);
        ResultadoPaginado<Filme> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

    // localhost:8080/filmes/slugCategoria/acaos
    @GetMapping("slugCategoria/{slug}")
    public List<Filme> recuperarFilmesPorSlugDaCategoria(@PathVariable("slug") String slug) {
        return filmeService.recuperarFilmesPorSlugDaCategoria(slug);
    }

    // http://localhost:8080/filmes/categoria/paginacao?pagina=0&tamanho=6&slug=acaos
    @GetMapping("categoria/paginacao")
    public ResultadoPaginado<Filme> recuperarFilmesPaginadosPorSlugDaCategoria(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "slug", defaultValue = "") String slug) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Filme> page = filmeService.recuperarFilmesPaginadosPorSlugDaCategoria(slug, pageable);
        ResultadoPaginado<Filme> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

}
