package br.com.maddytec.repositories;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p where p.id =:id")
    Optional<Pedido> obterPedidoPorId(@Param("id") Long id);

}
