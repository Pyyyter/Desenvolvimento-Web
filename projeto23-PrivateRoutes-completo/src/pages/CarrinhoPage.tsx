import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Pedido } from '../interfaces/Pedido';



const CarrinhoPage = () => {
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPedidos = async () => {
      try {
        const response = await axios.get<Pedido[]>(`/pedidos`);
        setPedidos(response.data);
      } catch (error) {
        console.error("Erro ao buscar pedidos", error);
      } finally {
        setLoading(false);
      }
    };

    fetchPedidos();
  }, []);

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (pedidos.length === 0) {
    return <div>Seu carrinho está vazio</div>;
  }

  return (
    <div>
      <h1>Carrinho</h1>
      <ul>
        {pedidos.map((pedido) => (
          <li key={pedido.id}>
            <div><strong>Pedido ID:</strong> {pedido.id}</div>
            <div><strong>Filme:</strong> {pedido.filme.nome}</div>
            <div><strong>Usuário ID:</strong> {pedido.userID}</div>
          </li>
        ))}
      </ul>
      <Link to="/lista-de-filmes">Voltar para a Lista de Filmes</Link>
    </div>
  );
};

export default CarrinhoPage;
