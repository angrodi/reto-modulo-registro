package pe.tintegro.apppacientesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto {

    private Integer idPaciente;
    private TipoDocumentoIdentidadDto tipoDocIdentidad;
    private String documentoIdentidad;
    private String apePaterno;
    private String apeMaterno;
    private String nombres;
    private SexoDto sexo;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private String direccion;
    private UbigeoDto ubigeo;

}
