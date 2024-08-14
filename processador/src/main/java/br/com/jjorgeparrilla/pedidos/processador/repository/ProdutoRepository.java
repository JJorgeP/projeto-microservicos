package br.com.jjorgeparrilla.pedidos.processador.repository;

import br.com.jjorgeparrilla.pedidos.processador.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
