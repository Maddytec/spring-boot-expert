package br.com.maddytec.http.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDTO implements Serializable {

    private Long cliente;
    private BigDecimal total;
    private List<ItemsPedidoDTO> listaItemPedido;

}
