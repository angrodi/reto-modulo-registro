package pe.tintegro.apppacientesbackend.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import pe.tintegro.apppacientesbackend.dao.PacienteAcompananteMapper;
import pe.tintegro.apppacientesbackend.model.PacienteAcompanante;
import pe.tintegro.apppacientesbackend.service.PacienteAcompananteService;

@Service
@Data
public class PacienteAcompananteServiceImpl implements PacienteAcompananteService {

    private final PacienteAcompananteMapper pacienteAcompananteMapper;

    @Override
    public PacienteAcompanante getPacienteAcompananteByIdPaciente(Integer idPaciente) {
        return this.pacienteAcompananteMapper.getPacienteAcompananteByIdPaciente(idPaciente);
    }

    @Override
    public Integer insertPacienteAcompanante(PacienteAcompanante acompanante) {
        return this.pacienteAcompananteMapper.insertPacienteAcompanante(
                acompanante.getIdPaciente(),
                acompanante.getTipoDocIdentidad().getIdTipoDocIdentidad(),
                acompanante.getDocumentoIdentidad(),
                acompanante.getApePaterno(),
                acompanante.getApeMaterno(),
                acompanante.getNombres(),
                acompanante.getFechaNacimiento(),
                acompanante.getParentesco().getIdParentesco(),
                acompanante.getTelefonoContacto(),
                acompanante.getDireccion(),
                acompanante.getUbigeo().getCodigo());
    }
}
