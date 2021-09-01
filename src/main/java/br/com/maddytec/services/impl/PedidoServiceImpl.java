package br.com.maddytec.services.impl;

import br.com.maddytec.entities.ItemPedido;
import br.com.maddytec.entities.Pedido;
import br.com.maddytec.entities.Produto;
import br.com.maddytec.exception.NegocioException;
import br.com.maddytec.http.controllers.request.PedidoRequest;
import br.com.maddytec.http.controllers.response.PedidoResponse;
import br.com.maddytec.repositories.ClienteRepository;
import br.com.maddytec.repositories.PedidoRepository;
import br.com.maddytec.repositories.ProdutoRepository;
import br.com.maddytec.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public Pedido salvar(PedidoRequest pedidoDTO){
        Pedido pedido = Pedido.builder()
                .total(pedidoDTO.getTotal())
                .dataPedido(LocalDate.now())
                .build();

        setCliente(pedidoDTO, pedido);
        setListaItemPedido(pedidoDTO, pedido);
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoPorId(Long idPedido) {
        return pedidoRepository.obterPedidoPorId(idPedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    private void setListaItemPedido(PedidoRequest pedidoDTO, Pedido pedido) {
        List<ItemPedido> listaItemPedido = pedidoDTO.getItens().stream()
                .map(itemPedidoDTO -> {
                    Produto produto = produtoRepository.findById(itemPedidoDTO.getProduto())
                            .orElseThrow(() -> new NegocioException("Produto não encontrado."));

                    return ItemPedido.builder()
                            .quantidade(itemPedidoDTO.getQuantidade())
                            .pedido(pedido)
                            .produto(produto)
                            .build();

                }).collect(Collectors.toList());

        pedido.setItens(listaItemPedido);
    }

    private void setCliente(PedidoRequest pedidoDTO, Pedido pedido) {
        clienteRepository.findById(pedidoDTO.getCliente())
                .map(cliente -> {
                    pedido.setCliente(cliente);
                    return cliente;
                }).orElseThrow(() -> new NegocioException("Cliente invalido."));
    }
}
