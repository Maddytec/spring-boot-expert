package br.com.maddytec.http.controllers;

import br.com.maddytec.entities.Produto;
import br.com.maddytec.services.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> lista(){
        return produtoService.lista();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto buscarPorId(@PathVariable("id") Long id){
        return produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> filtro(Produto filtro){
        return produtoService.filtro(filtro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@Valid @RequestBody Produto produto){
       return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(
            @Valid @PathVariable("id") Long id
            , @RequestBody Produto produto){
        produtoService.buscarPorId(id)
                .map( produtoBase -> {
                   modelMapper.map(produto, produtoBase);
                   produtoService.salvar(produtoBase);
                   return produtoBase;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){
        produtoService.buscarPorId(id)
                .map( produto -> {
                    produtoService.deletePorId(produto.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
