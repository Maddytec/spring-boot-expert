package br.com.maddytec.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Campo nome é obrigatório.")
    @Length(min = 3, message = "O campo nome deve possuir no minimo 3 letras")
    @Length(max = 100, message = "O campo nome deve possuir no maximo 100 letras")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Email(message = "Email invalido.")
    @Column(name = "email")
    private String email;

    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    @Column(name = "cpf")
    private String cpf;

    @Min(value = 10L, message = "DDD invalido.")
    @Column(name = "ddd")
    private Integer dddCelular;

    @Column(name = "celular")
    private String celular;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Pedido> listaPedido;

}
