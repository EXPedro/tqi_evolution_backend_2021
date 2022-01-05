package br.com.tqi.exp.me.empresta.service;

import br.com.tqi.exp.me.empresta.model.Login;
import br.com.tqi.exp.me.empresta.repository.LoginRepository;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*
classe com os serviços relacionados ao login do cliente
implementa UserDetailsService
outro reuisito do Spring Security
*/

@Service
@AllArgsConstructor
@ToString
public class LoginService implements UserDetailsService {
    private LoginRepository loginRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String USER_NOT_FOUND =
            "Usuário com email %s não encontrado";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return loginRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Login login) {
        boolean userExists = loginRepository
                .findByEmail(login.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("nome de usuário não está disponível");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(login.getPassword());
        login.setPassword(encodedPassword);
        loginRepository.save(login);

//        String token = UUID.randomUUID().toString();
//        return token;
        return UUID.randomUUID().toString();
    }


}
