package br.com.tqi.exp.me.empresta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DetalheEmprestimo {
    private Long codEmprestimo;
    private BigDecimal valor;
    private Integer prazo;
    private String email;
    private BigDecimal renda;
}
