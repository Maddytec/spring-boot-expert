package br.com.maddytec.repositories;

import br.com.maddytec.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
