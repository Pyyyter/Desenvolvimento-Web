package com.pedroribeiro.apirestful.controller;

import com.pedroribeiro.apirestful.model.Pedido;
import com.pedroribeiro.apirestful.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/user/{userId}")
    public List<Pedido> getPedidosByUserId(@PathVariable Long userId) {
        return pedidoService.recuperarPedidosPorUsuario(userId);
    }

    // Outros métodos do controlador...
}
