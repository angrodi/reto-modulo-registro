package pe.tintegro.apppacientesbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoIdentidad {

    private Integer idTipoDocIdentidad;
    private String descripcion;
    private String codigoIeds;
    private Integer estado;

}
