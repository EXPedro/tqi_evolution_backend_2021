package br.com.tqi.exp.me.empresta.repository;

import br.com.tqi.exp.me.empresta.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
repositório da tabela Login
*/

@Repository
public interface LoginRepository  extends JpaRepository<Login, Long>{
    Optional<Login> findByEmail(String email);
}
