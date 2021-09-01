package br.com.maddytec.http.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalheItemPedidoResponse {

    private String decricao;
    private BigDecimal precoUnitario;
    private Long quantidade;
}