package br.com.maddytec.services;

import br.com.maddytec.entities.Pedido;
import br.com.maddytec.http.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    public Pedido salvar(PedidoRequest pedidoDTO);

    public Optional<Pedido> obterPedidoPorId(Long idPedido);

    public List<Pedido> listarPedidos();
}
