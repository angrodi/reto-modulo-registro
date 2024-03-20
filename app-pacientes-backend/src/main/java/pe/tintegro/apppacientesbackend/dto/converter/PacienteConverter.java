package pe.tintegro.apppacientesbackend.dto.converter;

import org.mapstruct.Mapper;
import pe.tintegro.apppacientesbackend.dto.PacienteDto;
import pe.tintegro.apppacientesbackend.model.Paciente;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteConverter {

    PacienteDto modelToDto(Paciente model);
    Paciente dtoToModel(PacienteDto dto);

}
