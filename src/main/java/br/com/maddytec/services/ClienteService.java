package br.com.maddytec.services;

import br.com.maddytec.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public Cliente salvar(Cliente cliente);

    public List<Cliente> listaCliente();

    public List<Cliente> filtro(Cliente filtro);

    public List<Cliente> buscarPorNomeOuSobreNome(String nome);

    public List<Cliente> buscarNome(String nome);

    public Optional<Cliente> buscarPorId(Long id);

    public Optional<Cliente> buscarOptionalId(Long id);

    public Boolean existeEmail(String email);

    public void deletePorId(Long id);
}
