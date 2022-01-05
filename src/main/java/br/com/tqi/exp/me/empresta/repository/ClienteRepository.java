package br.com.tqi.exp.me.empresta.repository;

import br.com.tqi.exp.me.empresta.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
repositório da tabela Cliente
*/

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Query(value = "SELECT new br.com.tqi.exp.me.empresta.model.Cliente"
            + " (c.email, c.renda) "
            + "FROM Cliente c WHERE c.id = :id")
    Cliente detalhaCliente(@Param("id") Long id);
}
