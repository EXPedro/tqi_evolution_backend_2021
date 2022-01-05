package br.com.tqi.exp.me.empresta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/*
classe da entidade Cliente
annotations para validar campos
    antes de atualizar tabela
    conforme regras de negócio,
    com mensagens para "mapear"
    erros(exceptions)
construtores para queries
    e para mapstruct
*/

@Entity
@Table(name="cliente")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "O nome  deve ser informado")
    private String nome;

    @Column(nullable = false, length = 30, unique = true)
    @Email(message = "Email deve ser válido")
    private String email;

    @Column(nullable = false)
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @Column(nullable = false, length = 11)
    @Size(min = 11, message = "O CPF deve ter 11 dígitos")
    private String cpf;

    @Column(nullable = false, length = 9)
    @Size(min = 8, message = "O RG deve ter no maximo 8 dígitos")
    private String rg;

    @Column(nullable = false)
    @NotNull(message = "O endereco deve ser informado")
    private String endereco;

    @Column(nullable = false, precision = 16, scale = 2)
    @NotNull(message = "A renda  deve ser informada")
    private BigDecimal renda;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cadastroId")
    @ToString.Exclude
    private List<Emprestimo> emprestimos;

    public Cliente(
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

    public Cliente(
            String email,
            BigDecimal renda){
        this.email    = email;
        this.renda    = renda;
    }

    public Cliente(){
    }
}
