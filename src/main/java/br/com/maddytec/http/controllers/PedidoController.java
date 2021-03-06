package br.com.maddytec.http.controllers;

import br.com.maddytec.entities.Pedido;
import br.com.maddytec.enums.StatusPedidoEnum;
import br.com.maddytec.http.request.PedidoRequest;
import br.com.maddytec.http.request.StatusPedidoRequest;
import br.com.maddytec.http.response.DetalheItemPedidoResponse;
import br.com.maddytec.http.response.PedidoResponse;
import br.com.maddytec.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long salvar(@Valid @RequestBody PedidoRequest pedidoRequest){
        Pedido pedido = pedidoService.salvar(pedidoRequest);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public PedidoResponse obterPedidoPorId(@PathVariable("id") Long idPedido){
        return pedidoService.obterPedidoPorId(idPedido)
                .map( pedido -> {
                    List<DetalheItemPedidoResponse> detalheItens = null;
                    if(CollectionUtils.isEmpty(pedido.getItens())){
                        detalheItens = Collections.emptyList();
                } else {
                        detalheItens = pedido.getItens().stream()
                                .map(itemPedido -> DetalheItemPedidoResponse.builder()
                                        .decricao(itemPedido.getProduto().getDescricao())
                                        .precoUnitario(itemPedido.getProduto().getPreco())
                                        .quantidade(itemPedido.getQuantidade())
                                        .build()
                                ).collect(Collectors.toList());
                    }
                    return PedidoResponse.builder()
                            .id(pedido.getId())
                            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                            .cpf(pedido.getCliente().getCpf())
                            .nomeCliente(pedido.getCliente().getNome())
                            .total(pedido.getTotal())
                            .itens(detalheItens)
                            .statusPedido(pedido.getStatusPedido().name())
                            .build();
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido n??o encontrado"));
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatusPedido(@PathVariable("id") Long id, @RequestBody StatusPedidoRequest statusPedidoRequest){
        pedidoService.atualizarStatusPedido(id, StatusPedidoEnum.valueOf(statusPedidoRequest.getStatus()));
    }
}
