package br.com.tqi.exp.me.empresta.service;

import br.com.tqi.exp.me.empresta.mapper.ClienteMapper;
import br.com.tqi.exp.me.empresta.model.Cliente;
import br.com.tqi.exp.me.empresta.model.Login;
import br.com.tqi.exp.me.empresta.model.dto.ClienteDTO;
import br.com.tqi.exp.me.empresta.model.dto.mensagem.MsgRespostaDTO;
import br.com.tqi.exp.me.empresta.repository.ClienteRepository;
import br.com.tqi.exp.me.empresta.security.role.LoginRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
classe com os serviços relacionados a manipulação da tabela Cliente
métodos:
criaCliente()
criaMsgDeResposta()
detalhesCliente()
*/

@Service
@AllArgsConstructor
@ToString
@Builder
public class ClienteService {
    private final LoginService loginService;
    private final ClienteRepository clienteRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final ClienteMapper clienteMapper;

    //cria cliente a partir de modeloDTO e envia mensagem em caso de êxito
    public MsgRespostaDTO criaCliente(ClienteDTO clienteDTO){
        String encodedPassword = bCryptPasswordEncoder
                .encode(clienteDTO.getPassword());
                clienteDTO.setPassword(encodedPassword);
        Cliente clienteToSave = clienteMapper.toModel(clienteDTO);
        Cliente savedCliente = clienteRepository.save(clienteToSave);
        return criaMsgDeResposta(savedCliente.getEmail(), "Criado cliente com username ");
    }

    //cria a mensagem de êxito através do sucesso no método de criação de novas entidades Cliente
    private MsgRespostaDTO criaMsgDeResposta(String id, String message) {
        return MsgRespostaDTO
                .builder()
                .message(message + id)
                .build();
    }

    //utiliza a query para retornar dados conforme a lógica do negócio
    public Cliente detalhesCliente(Long id){
        return clienteRepository.detalhaCliente(id);
    }

    public String register(ClienteDTO clienteDTO) {

        Cliente cliente = clienteMapper.toModel(clienteDTO);

        String token = loginService.signUpUser(
                new Login(
                        cliente.getEmail(),
                        cliente.getPassword(),
                        LoginRole.USER
                )
        );
        return token;
    }
}
