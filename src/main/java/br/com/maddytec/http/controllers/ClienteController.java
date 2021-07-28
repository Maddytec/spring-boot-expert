package br.com.maddytec.http.controllers;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePorId(@PathVariable("id") Long id){
        Cliente cliente = clienteService.buscarId(id);
        if(cliente != null){
            clienteService.deletePorId(cliente.getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
