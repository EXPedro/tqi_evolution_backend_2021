package br.com.tqi.exp.me.empresta.controller;

import br.com.tqi.exp.me.empresta.model.DetalheEmprestimo;
import br.com.tqi.exp.me.empresta.model.dto.EmprestimoDTO;
import br.com.tqi.exp.me.empresta.model.dto.mensagem.MsgRespostaDTO;
import br.com.tqi.exp.me.empresta.model.Cliente;
import br.com.tqi.exp.me.empresta.model.Emprestimo;
import br.com.tqi.exp.me.empresta.service.ClienteService;
import br.com.tqi.exp.me.empresta.service.EmprestimoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
classe de controle da criação de Empréstimo por Cliente
métodos:
mapeado como POST -> criaEmprestimo(Long id, EmprestimoDTO),
    permitindo aos usuários cadastrarem empréstimos através de sua id de usuário
mapeado como GET -> listaEmprestimos(), lista todos os empréstimos de determinado
    usuário
mapeado como GET -> mostraEmprestimo(Long id), mostra o detalhamento do empréstimo,
    pelo código de empréstimo
*/

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmprestimoController {
    private EmprestimoService emprestimoService;
    private ClienteService cs;

    //endpoint para criar empréstimos, pela id do cliente
    @PostMapping("/emprestimos/{idCliente}")
    public MsgRespostaDTO criaEmprestimo(@PathVariable Long idCliente,
                                         @RequestBody EmprestimoDTO emprestimoDTO
    ) {
        return emprestimoService.criaEmprestimo(idCliente, emprestimoDTO);
    }

    //endpoint para criar empréstimos, pela id do cliente
    @GetMapping("/emprestimos/{idCliente}")
    public List<Emprestimo> listaEmprestimos(@PathVariable Long idCliente){
        return  emprestimoService.listaEmprestimos(idCliente);
    }

    //endpoint mostrar o detalhamento do empréstimo,
    //pela id do empréstimo, recupera-se a cadastroId do cliente
    //necessária para realizar o JOIN das duas consultas
    @GetMapping("/detalhes/{idEmprestimo}")
    public DetalheEmprestimo mostraEmprestimo(@PathVariable Long idEmprestimo) {
        Emprestimo e = emprestimoService.detalhesEmprestimo(idEmprestimo);
        Cliente c = cs.detalhesCliente(e.getCadastroId());
        return  DetalheEmprestimoController.detalheEmprestimo(e, c);
    }
}
