package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.tintegro.apppacientesbackend.dto.ParentescoDto;
import pe.tintegro.apppacientesbackend.model.Parentesco;

@Mapper(componentModel = "spring")
public interface ParentescoConverter {

    ParentescoDto modelToDto(Parentesco model);

    @Mappings({
            @Mapping(target = "activo", ignore = true)
    })
    Parentesco dtoToModel(ParentescoDto dto);

}
