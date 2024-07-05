package com.pedroribeiro.apirestful.service;

import com.pedroribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.model.Pedido;
import com.pedroribeiro.apirestful.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido criarPedido(Long userId, Filme filme) {
        Pedido pedido = new Pedido(userId, filme);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> recuperarPedidosPorUsuario(Long userId) {
        return pedidoRepository.findByUserId(userId);
    }

    public Pedido recuperarPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado com o ID: " + pedidoId));
    }

    @Transactional
    public Pedido atualizarPedido(Pedido pedido) {
        if (pedidoRepository.existsById(pedido.getId())) {
            return pedidoRepository.save(pedido);
        } else {
            throw new EntidadeNaoEncontradaException("Pedido não encontrado com o ID: " + pedido.getId());
        }
    }

    @Transactional
    public void removerPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado com o ID: " + pedidoId));
        pedidoRepository.delete(pedido);
    }
}
