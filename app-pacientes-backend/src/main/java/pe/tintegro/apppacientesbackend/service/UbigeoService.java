package pe.tintegro.apppacientesbackend.service;

import pe.tintegro.apppacientesbackend.dto.UbigeoDescCodDto;

import java.util.List;

public interface UbigeoService {

    List<UbigeoDescCodDto> getDepartamentos();
    List<UbigeoDescCodDto> getProvincias(String codigoDepartamento);
    List<UbigeoDescCodDto> getDistritos(String codigoDepartamento, String codigoProvincia);

}
