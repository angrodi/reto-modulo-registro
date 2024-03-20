package pe.tintegro.apppacientesbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ubigeo {

    private String codigo;
    private String descDepartamento;
    private String descProvincia;
    private String descDistrito;
    private String codigoDepartamento;
    private String codigoProvincia;
    private String codigoDistrito;
    private Integer estado;

}
