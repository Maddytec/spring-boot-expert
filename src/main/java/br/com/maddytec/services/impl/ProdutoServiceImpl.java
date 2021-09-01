package br.com.maddytec.services.impl;

import br.com.maddytec.entities.Produto;
import br.com.maddytec.repositories.ProdutoRepository;
import br.com.maddytec.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    ExampleMatcher exampleMatcher;

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> lista(){
        return produtoRepository.findAll();
    }

    public void atualizar(Produto produto){
        produtoRepository.save(produto);
    }

    public void remover(Long id){
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletePorId(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> filtro(Produto filtro){
        Example example = Example.of(filtro, exampleMatcher);
        return produtoRepository.findAll(example);
    }
}
