package pe.tintegro.apppacientesbackend.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import pe.tintegro.apppacientesbackend.dao.UbigeoMapper;
import pe.tintegro.apppacientesbackend.dto.UbigeoDescCodDto;
import pe.tintegro.apppacientesbackend.service.UbigeoService;

import java.util.List;

@Service
@Data
public class UbigeoServiceImpl implements UbigeoService {

    private final UbigeoMapper ubigeoMapper;

    @Override
    public List<UbigeoDescCodDto> getDepartamentos() {
        return this.ubigeoMapper.getDepartamentos();
    }

    @Override
    public List<UbigeoDescCodDto> getProvincias(String codigoDepartamento) {
        return this.ubigeoMapper.getProvincias(codigoDepartamento);
    }

    @Override
    public List<UbigeoDescCodDto> getDistritos(String codigoDepartamento, String codigoProvincia) {
        return this.ubigeoMapper.getDistritos(codigoDepartamento, codigoProvincia);
    }
}
