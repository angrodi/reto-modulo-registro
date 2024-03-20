package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.tintegro.apppacientesbackend.dto.SexoDto;
import pe.tintegro.apppacientesbackend.model.Sexo;

@Mapper(componentModel = "spring")
public interface SexoConverter {

    SexoDto modelToDto(Sexo model);

    @Mappings({
            @Mapping(target = "estado", ignore = true)
    })
    Sexo dtoToModel(SexoDto dto);

}
