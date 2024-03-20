package pe.tintegro.apppacientesbackend.service;

import pe.tintegro.apppacientesbackend.model.*;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> getAll();

    Optional<Paciente> getPacienteById(Integer idPaciente);

    Optional<Paciente> getPacienteByDocIdentidad(Integer tipoDocIdentidad, String docIdentidad);

    List<Paciente> getPacienteByNombresAndApellidos(String nombres, String apellidos);

    List<TipoDocumentoIdentidad> getTiposDocIdentidad();

    Integer insertPaciente(Paciente paciente);

    Integer insertPacientePacienteAcompanante(Paciente paciente, PacienteAcompanante acompanante);

    List<Sexo> getSexos();

    List<Parentesco> getParentescos();

    boolean deletePaciente(Integer idPaciente);
}
