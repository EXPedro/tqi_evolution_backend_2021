package br.com.tqi.exp.me.empresta.model;

import br.com.tqi.exp.me.empresta.model.validation.Regra3Meses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/*
classe da entidade Emprestimo
annotations para validar campos
    antes de atualizar tabela
    conforme regras de negócio,
    com mensagens para "mapear"
    erros(exceptions)
construtores para queries
    e para mapstruct
*/

@Entity
@Table(name="emprestimo")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Emprestimo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //valor do empréstimo
    @Column(name = "valor_total", nullable = false, precision = 16, scale = 2)
    @NotNull(message = "O valor total do empréstimo deve ser informado")
    private BigDecimal valorTotal;

    //data primeiro pagamento
    @Column(name = "data_inicial", nullable = false)
    @NotNull(message = "A data inicial do empréstimo deve ser informada")
    @Regra3Meses
    private LocalDate dataInicial;

    //prazo (max 60)
    @Column(nullable = false)
    @Max(value = 60, message = "O prazo máximo é de 60 meses")
    private Integer prazo;

    //prazo (max 60)
    @Column(nullable = false)
    private Long cadastroId;

    public Emprestimo(
            Long id,
            BigDecimal valorTotal,
            int prazo) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.prazo = prazo;
    }

    public Emprestimo(
            Long id,
            Long cadastroId
    ) {
        this.id = id;
        this.cadastroId = cadastroId;
    }

    public Emprestimo(
            Long id,
            BigDecimal valorTotal,
            int prazo,
            LocalDate dataInicial,
            Long cadastroId) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.prazo = prazo;
        this.dataInicial = dataInicial;
        this.cadastroId = cadastroId;
    }

    //construtor "padrão" para mapstruct
    public Emprestimo(){
    }
}
