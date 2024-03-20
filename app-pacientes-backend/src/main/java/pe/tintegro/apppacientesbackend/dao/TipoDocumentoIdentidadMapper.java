package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.model.TipoDocumentoIdentidad;

import java.util.List;

@Mapper
public interface TipoDocumentoIdentidadMapper {

    @Results(id = "tipoDocIdentidadResultMap", value = {
            @Result(property = "idTipoDocIdentidad", column = "id_tipo_documento_identidad"),
            @Result(property = "descripcion", column = "descripcion_tipo_documento_identidad"),
            @Result(property = "codigoIeds", column = "codigo_ieds"),
            @Result(property = "estado", column = "fl_estado")
    })
    @Select("SELECT * FROM \"Admision\".tc_tipo_documento_identidad WHERE fl_estado = '1'")
    List<TipoDocumentoIdentidad> getAll();

    @ResultMap("tipoDocIdentidadResultMap")
    @Select("SELECT * FROM \"Admision\".tc_tipo_documento_identidad WHERE id_tipo_documento_identidad = #{idTipoDocIdentidad}")
    TipoDocumentoIdentidad getTipoDocIdentidadById(@Param("idTipoDocIdentidad") Integer idTipoDocIdentidad);

}
