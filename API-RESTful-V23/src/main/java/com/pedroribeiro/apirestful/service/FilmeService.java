package com.pedroribeiro.apirestful.service;

import com.pedroribeiro.apirestful.exception.EntidadeDestacadaException;
import com.pedroribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.pedroribeiro.apirestful.exception.EntidadeTransienteException;
import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.repository.FilmeRepository;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> recuperarFilmes() {
        return filmeRepository.recuperarFilmesComCategoria();
    }

    public Filme cadastrarFilme(Filme filme) {
        if (filme.getId() == null) {
            return filmeRepository.save(filme);
        }
        else {
            throw new EntidadeDestacadaException(
                "Tentando cadastrar um objeto destacado.");
        }
    }

//    public Filme alterarFilme(Filme filme) {
//        if (filme.getId() == null) {
//            throw new EntidadeTransienteException("Tentando alterar um objeto transiente.");
//        }
//        else {
//            if (filmeRepository.findById(filme.getId()).isPresent()) {
//                return filmeRepository.save(filme);
//            }
//            else {
//                throw new EntidadeNaoEncontradaException(
//                        "Filme número " + filme.getId() + " não encontrado.");
//            }
//        }
//    }

    @Transactional
    public Filme alterarFilme(Filme filme) {
        if (filme.getId() == null) {
            throw new EntidadeTransienteException("Tentando alterar um objeto transiente.");
        }
        else {
            filmeRepository.recuperarPorIdComLock(filme.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                      "Filme número " + filme.getId() + " não encontrado."));
            return filmeRepository.save(filme);
        }
    }

    public Filme removerFilme(Long id) {
        Filme p = filmeRepository.recuperarFilmePorId(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Filme número " + id + " não encontrado."));
        filmeRepository.delete(p);
        return p;
    }

    public List<Filme> recuperarFilmesPorIdDaCategoria(Long idCategoria) {
        return filmeRepository.findByCategoriaId(idCategoria);
    }

    public List<Filme> recuperarFilmesComCategoria() {
        // return filmeRepository.findAll();
        return filmeRepository.recuperarFilmesComCategoria();
    }

    public Page<Filme> recuperarFilmesComPaginacao(String nome, Pageable pageable) {
        return filmeRepository.recuperarFilmesComPaginacao(nome, pageable);
    }

    public List<Filme> recuperarFilmesPorSlugDaCategoria(String slug) {
        return filmeRepository.findByCategoriaSlug(slug);
    }

    public Page<Filme> recuperarFilmesPaginadosPorSlugDaCategoria(String slug, Pageable pageable) {
        if(!slug.isEmpty()) {
            return filmeRepository.recuperarFilmesPaginadosPorSlugDaCategoria(slug, pageable);
        }
        else {
            return filmeRepository.recuperarFilmesPaginados(pageable);
        }
    }
}
