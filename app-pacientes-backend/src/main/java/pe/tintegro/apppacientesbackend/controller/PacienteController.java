package pe.tintegro.apppacientesbackend.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tintegro.apppacientesbackend.dto.*;
import pe.tintegro.apppacientesbackend.dto.converter.*;
import pe.tintegro.apppacientesbackend.error.exceptions.BadRequestException;
import pe.tintegro.apppacientesbackend.error.exceptions.NotFoundException;
import pe.tintegro.apppacientesbackend.model.Paciente;
import pe.tintegro.apppacientesbackend.model.PacienteAcompanante;
import pe.tintegro.apppacientesbackend.service.PacienteAcompananteService;
import pe.tintegro.apppacientesbackend.service.PacienteService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/paciente")
@Data
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteAcompananteService acompananteService;
    private final PacienteConverter pacienteConverter;
    private final PacienteAcompananteConverter acompananteConverter;
    private final TipoDocumentoIdentidadConverter tipoDocIdentidadConverter;
    private final SexoConverter sexoConverter;
    private final ParentescoConverter parentescoConverter;

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> createPaciente(@RequestBody PacientePacienteAcompananteDto body) {
        PacienteDto pacienteDto = body.getPaciente();
        PacienteAcompananteDto acompananteDto = body.getAcompanante();

        Paciente paciente = pacienteConverter.dtoToModel(pacienteDto);
        PacienteAcompanante acompanante = acompananteConverter.dtoToModel(acompananteDto);

        Integer idPaciente = null;

        if (paciente != null && acompanante != null) {
            idPaciente = pacienteService.insertPacientePacienteAcompanante(paciente, acompanante);

        } else if (paciente != null) {
            idPaciente = pacienteService.insertPaciente(paciente);

        } else {
            throw new BadRequestException("Datos de paciente incompletos");
        }

        return pacienteService.getPacienteById(idPaciente)
                .map(p -> {
                    PacienteAcompanante pacienteAcompanante = this.acompananteService.getPacienteAcompananteByIdPaciente(p.getIdPaciente());
                    PacienteDto pDto = pacienteConverter.modelToDto(paciente);
                    PacienteAcompananteDto aDto = acompananteConverter.modelToDto(pacienteAcompanante);

                    PacientePacienteAcompananteDto data = PacientePacienteAcompananteDto.builder()
                            .paciente(pDto)
                            .acompanante(aDto)
                            .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(data);
                })
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
    }

    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<?> getPacientes() {
        List<PacienteDto> data = this.pacienteService.getAll()
                .stream()
                .map(pacienteConverter::modelToDto)
                .collect(Collectors.toList());

        return buildResponse(data);
    }

    @GetMapping(
            value = "{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> getPaciente(@PathVariable("id") Integer idPaciente) {
        return this.pacienteService.getPacienteById(idPaciente)
                .map(paciente -> {
                    PacienteAcompanante pacienteAcompanante = this.acompananteService.getPacienteAcompananteByIdPaciente(paciente.getIdPaciente());
                    PacienteDto pacienteDto = pacienteConverter.modelToDto(paciente);
                    PacienteAcompananteDto pacienteAcompananteDto = acompananteConverter.modelToDto(pacienteAcompanante);

                    PacientePacienteAcompananteDto data = PacientePacienteAcompananteDto.builder()
                            .paciente(pacienteDto)
                            .acompanante(pacienteAcompananteDto)
                            .build();

                    return ResponseEntity.ok(data);
                })
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
    }

    @GetMapping(
            value = "/docidentidad",
            produces = "application/json"
    )
    public ResponseEntity<?> getPacienteByDocIdentidad(
            @RequestParam("docIdentidad") Optional<String> docIdentidad,
            @RequestParam("tipoDocIdentidad") Optional<String> tipoDocIdentidad) {

        if (!docIdentidad.isPresent() || !tipoDocIdentidad.isPresent()) {
            throw new BadRequestException("EL tipo y número de documento de identidad son requeridos");
        }

        return pacienteService.getPacienteByDocIdentidad(Integer.valueOf(tipoDocIdentidad.get()), docIdentidad.get())
                .map(paciente -> {
                    List<PacienteDto> data = Collections.singletonList(pacienteConverter.modelToDto(paciente));
                    return buildResponse(data);
                })
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
    }

    @GetMapping(
            value = "/nombesapellidos",
            produces = "application/json"
    )
    public ResponseEntity<?> getPacienteByNombresAndApellidos(
            @RequestParam("nombres") Optional<String> nombres,
            @RequestParam("apellidos") Optional<String> apellidos) {

        if (!nombres.isPresent() && !apellidos.isPresent()) {
            throw new BadRequestException("Debe ingresar nombres o apellidos");
        }

        List<PacienteDto> data = pacienteService
                .getPacienteByNombresAndApellidos(nombres.orElse(null), apellidos.orElse(null))
                .stream()
                .map(pacienteConverter::modelToDto)
                .collect(Collectors.toList());

        return buildResponse(data);
    }

    @GetMapping(
            value = "/docidentidad/tipo",
            produces = "application/json"
    )
    public ResponseEntity<?> getTiposDocIdentidad() {
        List<TipoDocumentoIdentidadDto> data = pacienteService
                .getTiposDocIdentidad()
                .stream()
                .map(tipoDocIdentidadConverter::modelToDto)
                .collect(Collectors.toList());

        return buildResponse(data);
    }

    @GetMapping(
            value = "/sexo",
            produces = "application/json"
    )
    public ResponseEntity<?> getSexos() {
        List<SexoDto> data = pacienteService
                .getSexos()
                .stream()
                .map(sexoConverter::modelToDto)
                .collect(Collectors.toList());

        return buildResponse(data);
    }

    @GetMapping(
            value = "/parentesco",
            produces = "application/json"
    )
    public ResponseEntity<?> getParentescos() {
        List<ParentescoDto> data = pacienteService
                .getParentescos()
                .stream()
                .map(parentescoConverter::modelToDto)
                .collect(Collectors.toList());

        return buildResponse(data);
    }

    @DeleteMapping(
            value = "{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> deletePaciente(@PathVariable("id") Integer idPaciente) {
        this.pacienteService.deletePaciente(idPaciente);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> buildResponse(List<?> data) {
        return ResponseEntity.ok(ResponseDto.builder().data(data).build());
    }
}
