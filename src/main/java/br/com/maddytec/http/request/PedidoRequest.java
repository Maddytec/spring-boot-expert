package br.com.maddytec.http.request;

import br.com.maddytec.validation.NotEmptyList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoRequest implements Serializable {

    @NotNull(message = "Código do cliente é obrigatório.")
    private Long cliente;

    @NotNull(message = "Total do pedido é obrigatório.")
    private BigDecimal total;

    @NotEmptyList(message = "É necessário que o pedido possua pelo menos um item.")
    private List<ItemPedidoRest> itens;

}
