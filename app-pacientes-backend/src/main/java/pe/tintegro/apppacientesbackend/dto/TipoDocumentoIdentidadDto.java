package pe.tintegro.apppacientesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoIdentidadDto {

    private Integer idTipoDocIdentidad;
    private String descripcion;
    private String codigoIeds;

}
