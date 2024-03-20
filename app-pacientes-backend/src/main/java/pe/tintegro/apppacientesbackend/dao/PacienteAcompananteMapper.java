package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.model.PacienteAcompanante;
import pe.tintegro.apppacientesbackend.model.Parentesco;
import pe.tintegro.apppacientesbackend.model.TipoDocumentoIdentidad;
import pe.tintegro.apppacientesbackend.model.Ubigeo;

import java.time.LocalDate;

@Mapper
public interface PacienteAcompananteMapper {

    @Results({
            @Result(property = "idPacienteAcompanante", column = "id_paciente_acompanante"),
            @Result(property = "idPaciente", column = "id_paciente"),
            @Result(property = "tipoDocIdentidad",  column = "id_tipo_docide",
                    javaType = TipoDocumentoIdentidad.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.TipoDocumentoIdentidadMapper.getTipoDocIdentidadById")),
            @Result(property = "documentoIdentidad", column = "no_docide"),
            @Result(property = "apePaterno", column = "no_apepat"),
            @Result(property = "apeMaterno", column = "no_apemat"),
            @Result(property = "nombres", column = "no_nombres"),
            @Result(property = "fechaNacimiento", column = "fe_nacimiento"),
            @Result(property = "parentesco", column = "id_parentesco",
                    javaType = Parentesco.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.ParentescoMapper.getParentescoById")),
            @Result(property = "telefonoContacto", column = "nu_telefo_contacto"),
            @Result(property = "direccion", column = "no_direccion"),
            @Result(property = "ubigeo", column = "co_ubigeo",
                    javaType = Ubigeo.class,
                    one = @One(select = "pe.tintegro.apppacientesbackend.dao.UbigeoMapper.getUbigeoById")),
    })
    @Select("SELECT * FROM \"Admision\".tb_paciente_acompanante tp WHERE tp.id_paciente = #{idPaciente}")
    PacienteAcompanante getPacienteAcompananteByIdPaciente(@Param("idPaciente") Integer idPaciente);

    @ResultType(Integer.class)
    @Select("select \"Admision\".fn_insertar_paciente_acompanante(#{idPaciente}, #{idTipoDocIdentidad}, #{docIdentidad}, #{apePaterno}, " +
            "#{apeMaterno}, #{nombres}, #{fechaNacimiento}, #{idParentesco}, #{telefonoContacto}, #{direccion}, #{codUbigeo})")
    Integer insertPacienteAcompanante(
            @Param("idPaciente") Integer idPaciente,
            @Param("idTipoDocIdentidad") Integer idTipoDocIdentidad,
            @Param("docIdentidad") String docIdentidad,
            @Param("apePaterno") String apePaterno,
            @Param("apeMaterno") String apeMaterno,
            @Param("nombres") String nombres,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("idParentesco") Integer idParentesco,
            @Param("telefonoContacto") String telefonoContacto,
            @Param("direccion") String direccion,
            @Param("codUbigeo") String codUbigeo
    );
}
