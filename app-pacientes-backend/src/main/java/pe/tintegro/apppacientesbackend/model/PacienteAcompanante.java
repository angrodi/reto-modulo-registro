package pe.tintegro.apppacientesbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteAcompanante {

    private Integer idPacienteAcompanante;
    private Integer idPaciente;
    private TipoDocumentoIdentidad tipoDocIdentidad;
    private String documentoIdentidad;
    private String apePaterno;
    private String apeMaterno;
    private String nombres;
    private LocalDate fechaNacimiento;
    private Parentesco parentesco;
    private String telefonoContacto;
    private String direccion;
    private Ubigeo ubigeo;

}
