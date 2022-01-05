package br.com.tqi.exp.me.empresta.security.config;


import br.com.tqi.exp.me.empresta.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
classe de configuração do WebSecurity
    esta configuração é um requisito para
    utilizar Spring Security
*/

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginService loginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()                                                  //utilizado durante desenvolvimento,
                                                                                   // remover em produção
                .authorizeRequests()
                    .antMatchers("/api/v*/cadastro/**").permitAll()     //manter acesso a este endpoint
                    .antMatchers("/api/v*/emprestimos/**").permitAll()  //alterar permissões para este endpoind
                    .antMatchers("/api/v*/detalhes/**").permitAll()     //alterar permissões para este endpoind
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    //.loginPage("/login")    //redirecionar para página de login customizada
                    //.defaultSuccessUrl(url-para-redirecionar)     //redirecionar para página criação de empréstimos
                    .permitAll();   //permite acesso a todos à página de login
//                    .and()
//                .logout()         //estabelece controle para logout
//                    .permitAll()
//                    .and()
//                .rememberMe();    //estabelece check para permitir ao browser lembrar do login
        System.out.println("--- Configurado HTTPSecurity");
    }

    //ignorar acesso ao console do banco de dados
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("--- Configurado AuthenticationBuilder");
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(loginService);
        System.out.println("--- Configurado DAOAuthentication");
        return provider;
    }
}

