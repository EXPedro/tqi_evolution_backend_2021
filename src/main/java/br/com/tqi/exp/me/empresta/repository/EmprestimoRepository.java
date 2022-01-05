package br.com.tqi.exp.me.empresta.repository;

import br.com.tqi.exp.me.empresta.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
repositório da tabela Emprestimo
*/

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

    @Query(value = "SELECT new br.com.tqi.exp.me.empresta.model.Emprestimo"
                 + " (e.id, e.valorTotal, e.prazo) "
                 + "FROM Emprestimo e WHERE e.cadastroId = :id")
    List<Emprestimo> findAllClienteEmprestimos(@Param("id") Long id);

    @Query(value = "SELECT new br.com.tqi.exp.me.empresta.model.Emprestimo"
                 + " (e.id, e.valorTotal, e.prazo, e.dataInicial, e.cadastroId) "
                 + "FROM Emprestimo e WHERE e.id = :id")
    Emprestimo detalhaEmprestimo(@Param("id") Long id);
}
