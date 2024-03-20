package pe.tintegro.apppacientesbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sexo {

    private Integer idSexo;
    private String descripcion;
    private Integer estado;

}
