package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.tintegro.apppacientesbackend.dto.TipoDocumentoIdentidadDto;
import pe.tintegro.apppacientesbackend.model.TipoDocumentoIdentidad;

@Mapper(componentModel = "spring")
public interface TipoDocumentoIdentidadConverter {

    TipoDocumentoIdentidadDto modelToDto(TipoDocumentoIdentidad model);

    @Mappings({
            @Mapping(target = "estado", ignore = true)
    })
    TipoDocumentoIdentidad dtoToModel(TipoDocumentoIdentidadDto dto);
}
