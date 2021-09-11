package br.com.maddytec.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Campo descrição é obrigatório.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull(message = "Campo preço não pode ser nulo.")
    @Column(name = "preco")
    private BigDecimal preco;
}
