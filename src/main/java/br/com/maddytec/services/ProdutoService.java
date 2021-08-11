package br.com.maddytec.services;

import br.com.maddytec.entities.Produto;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    public Produto salvar(Produto produto);

    public List<Produto> lista();

    public void atualizar(Produto produto);

    public void remover(Long id);

    public Optional<Produto> buscarPorId(Long id);

    public void deletePorId(Long id);

    public List<Produto> filtro(Produto filtro);
}
