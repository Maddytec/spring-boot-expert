package br.com.maddytec.services;

import br.com.maddytec.entities.Pedido;
import br.com.maddytec.http.controllers.dto.PedidoDTO;

public interface PedidoService {

    public Pedido salvar(PedidoDTO pedidoDTO);
}
