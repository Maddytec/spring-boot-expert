package br.com.maddytec.http.request;

import br.com.maddytec.http.response.DetalheItemPedidoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDetalheDTO {

    private Long codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private List<DetalheItemPedidoResponse> itens;

}
