package br.com.tqi.exp.me.empresta.model.dto;

import br.com.tqi.exp.me.empresta.model.Emprestimo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/*
classe para mapeamento das entidades no modelo DTO
construtor:
com todos os argumentos, menos id, autogerada pelo banco de dados
*/

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ClienteDTO {

    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String rg;

    @NotEmpty
    private String endereco;

    @NotEmpty
    private BigDecimal renda;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cadastroId")
    @ToString.Exclude
    private List<Emprestimo> emprestimos;

     public ClienteDTO(
            String nome,
            String email,
            String password,
            String cpf,
            String rg,
            String endereco,
            BigDecimal renda){
        this.nome     = nome;
        this.email    = email;
        this.password = password;
        this.cpf      = cpf     ;
        this.rg       = rg      ;
        this.endereco = endereco;
        this.renda    = renda;
    }
//
    public ClienteDTO(
            String email,
            BigDecimal renda){
        this.email    = email;
        this.renda    = renda;
    }

    public ClienteDTO(){
    }
}
