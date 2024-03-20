package pe.tintegro.apppacientesbackend.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.tintegro.apppacientesbackend.dao.PacienteMapper;
import pe.tintegro.apppacientesbackend.dao.ParentescoMapper;
import pe.tintegro.apppacientesbackend.dao.SexoMapper;
import pe.tintegro.apppacientesbackend.dao.TipoDocumentoIdentidadMapper;
import pe.tintegro.apppacientesbackend.model.*;
import pe.tintegro.apppacientesbackend.service.PacienteService;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PacienteServiceImpl implements PacienteService {

    private final PacienteMapper pacienteMapper;
    private final TipoDocumentoIdentidadMapper tipoDocIdentidadMapper;
    private final SexoMapper sexoMapper;
    private final ParentescoMapper parentescoMapper;

    @Override
    public List<Paciente> getAll() {
        return this.pacienteMapper.getAll();
    }

    @Override
    public Optional<Paciente> getPacienteById(Integer idPaciente) {
        return this.pacienteMapper.getPacienteById(idPaciente);
    }

    @Override
    public Optional<Paciente> getPacienteByDocIdentidad(Integer tipoDocIdentidad, String docIdentidad) {
        return this.pacienteMapper.getPacienteByDocIdentidad(tipoDocIdentidad, docIdentidad);
    }

    @Override
    public List<Paciente> getPacienteByNombresAndApellidos(String nombres, String apellidos) {
        if (StringUtils.hasLength(nombres) && StringUtils.hasLength(apellidos)) {
            return this.pacienteMapper.getPacienteByNombresAndApellidos(nombres, apellidos);

        } else if (StringUtils.hasLength(nombres)) {
            return this.pacienteMapper.getPacienteByNombres(nombres);

        } else if (StringUtils.hasLength(apellidos)) {
            return this.pacienteMapper.getPacienteByApellidos(apellidos);

        } else {
            return null;
        }
    }

    @Override
    public List<TipoDocumentoIdentidad> getTiposDocIdentidad() {
        return this.tipoDocIdentidadMapper.getAll();
    }

    @Override
    public Integer insertPaciente(Paciente paciente) {
        return this.pacienteMapper.insertPaciente(
                paciente.getTipoDocIdentidad().getIdTipoDocIdentidad(),
                paciente.getDocumentoIdentidad(),
                paciente.getApePaterno(),
                paciente.getApeMaterno(),
                paciente.getNombres(),
                paciente.getSexo().getIdSexo(),
                paciente.getFechaNacimiento(),
                paciente.getLugarNacimiento(),
                paciente.getDireccion(),
                paciente.getUbigeo().getCodigo());
    }

    @Override
    public Integer insertPacientePacienteAcompanante(Paciente paciente, PacienteAcompanante acompanante) {
        return this.pacienteMapper.insertPacientePacienteAcompanante(
                paciente.getTipoDocIdentidad().getIdTipoDocIdentidad(),
                paciente.getDocumentoIdentidad(),
                paciente.getApePaterno(),
                paciente.getApeMaterno(),
                paciente.getNombres(),
                paciente.getSexo().getIdSexo(),
                paciente.getFechaNacimiento(),
                paciente.getLugarNacimiento(),
                paciente.getDireccion(),
                paciente.getUbigeo().getCodigo(),

                acompanante.getTipoDocIdentidad().getIdTipoDocIdentidad(),
                acompanante.getDocumentoIdentidad(),
                acompanante.getApePaterno(),
                acompanante.getApeMaterno(),
                acompanante.getNombres(),
                acompanante.getFechaNacimiento(),
                acompanante.getParentesco().getIdParentesco(),
                acompanante.getTelefonoContacto(),
                acompanante.getDireccion(),
                acompanante.getUbigeo().getCodigo()
        );
    }

    @Override
    public List<Sexo> getSexos() {
        return this.sexoMapper.getAll();
    }

    @Override
    public List<Parentesco> getParentescos() {
        return this.parentescoMapper.getAll();
    }

    @Override
    public boolean deletePaciente(Integer idPaciente) {
        return this.pacienteMapper.deletePaciente(idPaciente);
    }


}
