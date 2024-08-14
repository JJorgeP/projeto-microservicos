package br.com.jjorgeparrilla.pedidos.processador.service;

import br.com.jjorgeparrilla.pedidos.processador.entity.ItemPedido;
import br.com.jjorgeparrilla.pedidos.processador.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public void save(List<ItemPedido> itens) {

        itens.forEach(item -> {
            produtoRepository.save(item.getProduto());
        }   );
    }
}
