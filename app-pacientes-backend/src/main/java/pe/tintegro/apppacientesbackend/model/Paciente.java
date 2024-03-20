package pe.tintegro.apppacientesbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private Integer idPaciente;
    private TipoDocumentoIdentidad tipoDocIdentidad;
    private String documentoIdentidad;
    private String apePaterno;
    private String apeMaterno;
    private String nombres;
    private Sexo sexo;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private String direccion;
    private Ubigeo ubigeo;

}
