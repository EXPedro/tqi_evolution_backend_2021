package br.com.tqi.exp.me.empresta.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

/*
classe para mapeamento das entidades no modelo DTO
construtor:
dois, com os argumentos utilizados como parâmetro nas queries
*/

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmprestimoDTO {

    private Long id;

    @NotEmpty
    private BigDecimal valorTotal;

    @NotEmpty
    private LocalDate dataInicial;

    @NotEmpty
    private int prazo;

    @NotEmpty
    private Long cadastroId;

    //construtor "padrão" para mapstruct
    public EmprestimoDTO(){
    }

    //construtor com parâmetros da querie
    public EmprestimoDTO(
            BigDecimal valorTotal,
            LocalDate dataInicial,
            int prazo) {
        this.valorTotal = valorTotal;
        this.dataInicial = dataInicial;
        this.prazo = prazo;
    }

    //construtor com parâmetros da querie
    public EmprestimoDTO(
            Long cadastroId) {
        this.cadastroId = cadastroId;
    }
}
