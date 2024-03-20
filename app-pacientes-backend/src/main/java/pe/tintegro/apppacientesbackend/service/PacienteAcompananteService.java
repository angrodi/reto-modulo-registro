package pe.tintegro.apppacientesbackend.service;

import pe.tintegro.apppacientesbackend.model.PacienteAcompanante;

public interface PacienteAcompananteService {

    PacienteAcompanante getPacienteAcompananteByIdPaciente(Integer idPaciente);

    Integer insertPacienteAcompanante(PacienteAcompanante acompanante);

}
