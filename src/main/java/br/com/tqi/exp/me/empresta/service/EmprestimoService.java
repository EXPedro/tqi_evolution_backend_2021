package br.com.tqi.exp.me.empresta.service;

import br.com.tqi.exp.me.empresta.model.dto.EmprestimoDTO;
import br.com.tqi.exp.me.empresta.model.dto.mensagem.MsgRespostaDTO;
import br.com.tqi.exp.me.empresta.mapper.EmprestimoMapper;
import br.com.tqi.exp.me.empresta.model.Emprestimo;
import br.com.tqi.exp.me.empresta.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
classe com os serviços relacionados a manipulação da tabela Emprestimo
métodos:
criaMsgDeResposta(), constrói mensagem de sucesso na criação de Empréstimo
listaEmprestimos(Long id), lista os empréstimos, de acordo com id do cliente
criaEmprestimo(Long id, EmprestimoDTO emprestimo DTO), cria empréstimos,
    utilizando a id do usuário para preencher o campo cadastroId da entidade
    Empréstimo
detalhesEmprestimo(long id), lista todos os empréstimos feitos pelo cliente
*/

@Service
@AllArgsConstructor
@ToString
@Builder
public class EmprestimoService {
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private final EmprestimoMapper emprestimoMapper;

    //cria a mensagem de êxito através do sucesso no método de criação de novas entidades Emprestimo
    private MsgRespostaDTO criaMsgDeResposta(Long id, String message) {
        return MsgRespostaDTO
                .builder()
                .message(message + id)
                .build();
    }

    //lista empréstimos feitos pelo cliente
    public List<Emprestimo> listaEmprestimos(Long id){
        return emprestimoRepository.findAllClienteEmprestimos( id);
    }

    //cria um novo empréstimo para o cliente
    public MsgRespostaDTO criaEmprestimo(Long id,
                                         EmprestimoDTO emprestimoDTO){
        emprestimoDTO.setCadastroId(id);

        Emprestimo emprestimoToSave = emprestimoMapper.toModel(emprestimoDTO);
        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimoToSave);
        return criaMsgDeResposta(savedEmprestimo.getId(), "Criado empréstimo com ID ");
    }

    //detalha o empréstimo feito a um cliente
    public Emprestimo detalhesEmprestimo(Long id){
        return emprestimoRepository.detalhaEmprestimo(id);
    }
}
