package br.com.tqi.exp.me.empresta.mapper;

import br.com.tqi.exp.me.empresta.model.dto.ClienteDTO;
import br.com.tqi.exp.me.empresta.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/*
mapeamento de entidades para objetos DTO e vice-versa
conforme documentação do mapstruct
*/

@Component
@Mapper
public interface ClienteMapper {
        ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
        Cliente toModel(ClienteDTO clienteDTO);
        ClienteDTO toDTO(Cliente cliente);
}
