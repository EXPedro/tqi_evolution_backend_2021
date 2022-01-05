package br.com.tqi.exp.me.empresta.mapper;

import br.com.tqi.exp.me.empresta.model.Emprestimo;
import br.com.tqi.exp.me.empresta.model.dto.EmprestimoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/*
mapeamento de entidades para objetos DTO e vice-versa
conforme documentação do mapstruct
*/

@Mapper
@Component
public interface EmprestimoMapper {
        EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);
        Emprestimo toModel(EmprestimoDTO emprestimoDTO);
        EmprestimoDTO toDTO(Emprestimo emprestimo);
}
