package pe.tintegro.apppacientesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteAcompananteDto {

    private Integer idPacienteAcompanante;
    private Integer idPaciente;
    private TipoDocumentoIdentidadDto tipoDocIdentidad;
    private String documentoIdentidad;
    private String apePaterno;
    private String apeMaterno;
    private String nombres;
    private LocalDate fechaNacimiento;
    private ParentescoDto parentesco;
    private String telefonoContacto;
    private String direccion;
    private UbigeoDto ubigeo;

}
