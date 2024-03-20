package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.tintegro.apppacientesbackend.dto.UbigeoDto;
import pe.tintegro.apppacientesbackend.model.Ubigeo;

@Mapper(componentModel = "spring")
public interface UbigeoConverter {

    UbigeoDto modelToDto(Ubigeo model);

    @Mappings({
            @Mapping(target = "estado", ignore = true)
    })
    Ubigeo dtoToModel(UbigeoDto dto);
}
