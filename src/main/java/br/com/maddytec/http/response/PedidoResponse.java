package br.com.maddytec.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponse {
    private Long id;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private String statusPedido;
    private List<DetalheItemPedidoResponse> itens;

}
