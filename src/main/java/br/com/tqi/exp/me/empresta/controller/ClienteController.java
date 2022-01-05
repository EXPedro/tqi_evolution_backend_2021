package br.com.tqi.exp.me.empresta.controller;

import br.com.tqi.exp.me.empresta.model.dto.ClienteDTO;
import br.com.tqi.exp.me.empresta.model.dto.mensagem.MsgRespostaDTO;
import br.com.tqi.exp.me.empresta.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
classe de controle da criação (cadastro) de Clientes
métodos:
mapeado como POST -> criaCliente()
*/

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    //define como endpoint o endereço "/cadastro"
    @PostMapping("/cadastro")
    public MsgRespostaDTO criaCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteService.register(clienteDTO);
        return clienteService.criaCliente(clienteDTO);
    }
}
