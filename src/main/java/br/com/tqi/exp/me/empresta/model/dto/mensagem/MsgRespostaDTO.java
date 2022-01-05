package br.com.tqi.exp.me.empresta.model.dto.mensagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
classe com atribuição de construir a resposta nos casos de sucesso na criação de entidades
*/

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgRespostaDTO {
    private String message;
}
