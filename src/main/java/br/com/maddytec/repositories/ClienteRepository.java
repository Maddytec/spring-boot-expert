package br.com.maddytec.repositories;

import br.com.maddytec.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Boolean existsByEmail(String email);

    void deleteByEmail(String email);

}
