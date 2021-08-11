package br.com.maddytec.http.controllers;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientes(){
        return clienteService.listaCliente();
    }


    @GetMapping("/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> nomeCliente(@PathVariable("nome") String nome){
        return clienteService.buscarPorNomeOuSobreNome(nome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente nomeCliente(@PathVariable("id") Long id){
        return clienteService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Cadastrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePorId(@PathVariable("id") Long id){
        clienteService.buscarPorId(id)
                .map( cliente -> {
                    clienteService.buscarPorId(cliente.getId());
                        return cliente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") Long id, @RequestBody Cliente clienteRequest){
      clienteService.buscarOptionalId(id).map(
                clienteBase -> {
                    modelMapper.map(clienteRequest, clienteBase);
                    clienteService.salvar(clienteBase);
                    return clienteBase;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> filtro(Cliente filtro){
       return clienteService.filtro(filtro);
    }
}
