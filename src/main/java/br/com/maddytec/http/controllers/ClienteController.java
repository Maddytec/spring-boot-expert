package br.com.maddytec.http.controllers;

import br.com.maddytec.entities.Cliente;
import br.com.maddytec.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody Cliente clienteRequest){

      return clienteService.buscarOptionalId(id).map(
                clienteBase -> {
                    modelMapper.map(clienteRequest, clienteBase);
                    clienteService.salvar(clienteBase);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
        }
}
