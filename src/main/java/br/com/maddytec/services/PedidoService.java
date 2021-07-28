package br.com.maddytec.services;

import br.com.maddytec.entities.Pedido;
import br.com.maddytec.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
