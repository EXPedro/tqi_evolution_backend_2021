package br.com.tqi.exp.me.empresta.controller;

import br.com.tqi.exp.me.empresta.model.Cliente;
import br.com.tqi.exp.me.empresta.model.DetalheEmprestimo;
import br.com.tqi.exp.me.empresta.model.Emprestimo;

public class DetalheEmprestimoController {

    public static DetalheEmprestimo detalheEmprestimo(Emprestimo e, Cliente c){
        return
                new DetalheEmprestimo(  e.getId(),
                        e.getValorTotal(),
                        e.getPrazo(),
                        c.getEmail(),
                        c.getRenda());
    }
}
