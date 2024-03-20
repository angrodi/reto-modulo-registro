package pe.tintegro.apppacientesbackend.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.tintegro.apppacientesbackend.dto.UbigeoDescCodDto;
import pe.tintegro.apppacientesbackend.dto.ResponseDto;
import pe.tintegro.apppacientesbackend.service.UbigeoService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/ubigeo")
@Data
public class UbigeoController {

    private final UbigeoService ubigeoService;

    @GetMapping(
            value = "departamentos",
            produces = "application/json"
    )
    public ResponseEntity<?> getDepartamentos() {
        List<UbigeoDescCodDto> departamentos = this.ubigeoService.getDepartamentos();
        return buildResponse(departamentos);
    }

    @GetMapping(
            value = "provincias",
            produces = "application/json"
    )
    public ResponseEntity<?> getProvincias(@RequestParam("codDepartamento") String codDepartamento) {
        List<UbigeoDescCodDto> provincias = this.ubigeoService.getProvincias(codDepartamento);
        return buildResponse(provincias);
    }

    @GetMapping(
            value = "distritos",
            produces = "application/json"
    )
    public ResponseEntity<?> getDistritos(
            @RequestParam("codDepartamento") String codDepartamento,
            @RequestParam("codProvincia") String codProvincia) {

        List<UbigeoDescCodDto> distritos = this.ubigeoService.getDistritos(codDepartamento, codProvincia);
        return buildResponse(distritos);
    }

    private ResponseEntity<?> buildResponse(List<UbigeoDescCodDto> data) {
        return ResponseEntity.ok(ResponseDto.builder().data(data).build());
    }
}
