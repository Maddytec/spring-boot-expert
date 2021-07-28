package br.com.maddytec.services;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Cliente> buscarPorNomeOuSobreNome(String nome){
        return clienteRepository.findByNomeContaining(nome);
    }

    public List<Cliente> buscarNome(String nome){
        return clienteRepository.findByNomeLike(nome);
    }

    public Cliente buscarId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Boolean existeEmail(String email){
        return clienteRepository.existsByEmail(email);
    }

}
