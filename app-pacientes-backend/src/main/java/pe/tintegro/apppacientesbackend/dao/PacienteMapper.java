package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.model.Paciente;
import pe.tintegro.apppacientesbackend.model.Sexo;
import pe.tintegro.apppacientesbackend.model.TipoDocumentoIdentidad;
import pe.tintegro.apppacientesbackend.model.Ubigeo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PacienteMapper {

    @Results(id = "pacienteResultMap", value = {
            @Result(property = "idPaciente", column = "id_paciente"),
            @Result(property = "tipoDocIdentidad",  column = "id_tipo_docide",
                    javaType = TipoDocumentoIdentidad.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.TipoDocumentoIdentidadMapper.getTipoDocIdentidadById")),
            @Result(property = "documentoIdentidad", column = "no_docide"),
            @Result(property = "apePaterno", column = "no_apepat"),
            @Result(property = "apeMaterno", column = "no_apemat"),
            @Result(property = "nombres", column = "no_nombres"),
            @Result(property = "sexo", column = "id_sexo",
                    javaType = Sexo.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.SexoMapper.getSexoById")),
            @Result(property = "fechaNacimiento", column = "fe_nacimiento"),
            @Result(property = "lugarNacimiento", column = "no_lugar_nacimiento"),
            @Result(property = "direccion", column = "no_direccion"),
            @Result(property = "ubigeo", column = "co_ubigeo",
                    javaType = Ubigeo.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.UbigeoMapper.getUbigeoById")),
    })
    @Select("SELECT * FROM \"Admision\".tb_paciente")
    List<Paciente> getAll();

    @ResultMap("pacienteResultMap")
    @Select("SELECT * FROM \"Admision\".tb_paciente tp WHERE tp.id_paciente = #{idPaciente}")
    Optional<Paciente> getPacienteById(@Param("idPaciente") Integer idPaciente);

    @ResultMap("pacienteResultMap")
    @Select("SELECT * FROM \"Admision\".tb_paciente tp WHERE tp.id_tipo_docide =  #{tipoDocIdentidad} AND tp.no_docide = #{docIdentidad}")
    Optional<Paciente> getPacienteByDocIdentidad(
            @Param("tipoDocIdentidad") Integer tipoDocIdentidad,
            @Param("docIdentidad") String docIdentidad);

    @ResultMap("pacienteResultMap")
    @Select("SELECT * FROM \"Admision\".tb_paciente tp WHERE tp.no_nombres = #{nombres}")
    List<Paciente> getPacienteByNombres(@Param("nombres") String nombres);

    @ResultMap("pacienteResultMap")
    @Select("SELECT * FROM \"Admision\".tb_paciente tp WHERE CONCAT(tp.no_apepat, ' ', tp.no_apemat) = #{apellidos}")
    List<Paciente> getPacienteByApellidos(@Param("apellidos") String apellidos);

    @ResultMap("pacienteResultMap")
    @Select("SELECT * FROM \"Admision\".tb_paciente tp WHERE tp.no_nombres = #{nombres} AND CONCAT(tp.no_apepat, ' ', tp.no_apemat) = #{apellidos}")
    List<Paciente> getPacienteByNombresAndApellidos(
            @Param("nombres") String nombres,
            @Param("apellidos") String apellidos);

    @ResultType(Integer.class)
    @Select("select \"Admision\".fn_insertar_paciente(#{idTipoDocIdentidad}, #{docIdentidad}, #{apePaterno}, " +
            "#{apeMaterno}, #{nombres}, #{idSexo}, #{fechaNacimiento}, #{lugarNacimiento}, #{direccion}, #{codUbigeo})")
    Integer insertPaciente(
            @Param("idTipoDocIdentidad") Integer idTipoDocIdentidad,
            @Param("docIdentidad") String docIdentidad,
            @Param("apePaterno") String apePaterno,
            @Param("apeMaterno") String apeMaterno,
            @Param("nombres") String nombres,
            @Param("idSexo") Integer idSexo,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("lugarNacimiento") String lugarNacimiento,
            @Param("direccion") String direccion,
            @Param("codUbigeo") String codUbigeo
    );

    @ResultType(Integer.class)
    @Select("select \"Admision\".fn_insertar_paciente_y_acompanante(#{idTipoDocIdentidad}, #{docIdentidad}, #{apePaterno}, " +
            "#{apeMaterno}, #{nombres}, #{idSexo}, #{fechaNacimiento}, #{lugarNacimiento}, #{direccion}, #{codUbigeo}, " +
            "#{idTipoDocIdentidadAcomp}, #{docIdentidadAcomp}, #{apePaternoAcomp}, #{apeMaternoAcomp}, #{nombresAcomp}, " +
            "#{fechaNacimientoAcomp}, #{idParentesco}, #{telefonoContactoAcomp}, #{direccionAcomp}, #{codUbigeoAcomp})")
    Integer insertPacientePacienteAcompanante(
            @Param("idTipoDocIdentidad") Integer idTipoDocIdentidad,
            @Param("docIdentidad") String docIdentidad,
            @Param("apePaterno") String apePaterno,
            @Param("apeMaterno") String apeMaterno,
            @Param("nombres") String nombres,
            @Param("idSexo") Integer idSexo,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("lugarNacimiento") String lugarNacimiento,
            @Param("direccion") String direccion,
            @Param("codUbigeo") String codUbigeo,

            @Param("idTipoDocIdentidadAcomp") Integer idTipoDocIdentidadAcomp,
            @Param("docIdentidadAcomp") String docIdentidadAcomp,
            @Param("apePaternoAcomp") String apePaternoAcomp,
            @Param("apeMaternoAcomp") String apeMaternoAcomp,
            @Param("nombresAcomp") String nombresAcomp,
            @Param("fechaNacimientoAcomp") LocalDate fechaNacimientoAcomp,
            @Param("idParentesco") Integer idParentesco,
            @Param("telefonoContactoAcomp") String telefonoContactoAcomp,
            @Param("direccionAcomp") String direccionAcomp,
            @Param("codUbigeoAcomp") String codUbigeoAcomp
    );

    @ResultType(Boolean.class)
    @Select("select \"Admision\".fn_eliminar_paciente_y_acompanante(#{idPaciente})")
    Boolean deletePaciente(@Param("idPaciente") Integer idPaciente);
}
