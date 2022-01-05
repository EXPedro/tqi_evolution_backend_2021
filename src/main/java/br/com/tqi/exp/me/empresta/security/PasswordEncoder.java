package br.com.tqi.exp.me.empresta.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
Classe para encriptar a senha,
evitando exposição da mesma nas requisições Web
*/

@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}