package pe.tintegro.apppacientesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacientePacienteAcompananteDto {

    private PacienteDto paciente;
    private PacienteAcompananteDto acompanante;

}
