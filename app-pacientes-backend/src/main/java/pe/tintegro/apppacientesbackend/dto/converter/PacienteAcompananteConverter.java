package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import pe.tintegro.apppacientesbackend.dto.PacienteAcompananteDto;
import pe.tintegro.apppacientesbackend.model.PacienteAcompanante;

@Mapper(componentModel = "spring")
public interface PacienteAcompananteConverter {

    PacienteAcompananteDto modelToDto(PacienteAcompanante model);
    PacienteAcompanante dtoToModel(PacienteAcompananteDto dto);

}
