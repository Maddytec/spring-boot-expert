package br.com.maddytec.services;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

    public List<Cliente> filtro(Cliente filtro){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, exampleMatcher);
        return clienteRepository.findAll(example);
    }


    public List<Cliente> buscarPorNomeOuSobreNome(String nome){
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> buscarNome(String nome){
        return clienteRepository.findByNomeLike(nome);
    }

    public Cliente buscarId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Optional<Cliente> buscarOptionalId(Long id){
        return clienteRepository.findById(id);
    }

    public Boolean existeEmail(String email){
        return clienteRepository.existsByEmail(email);
    }

    public void deletePorId(Long id){
        clienteRepository.deleteById(id);
    }

}
