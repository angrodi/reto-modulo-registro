package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.model.Sexo;
import pe.tintegro.apppacientesbackend.model.TipoDocumentoIdentidad;

import java.util.List;

@Mapper
public interface SexoMapper {

    @Results(id = "sexoResultMap", value = {
            @Result(property = "idSexo", column = "id_sexo"),
            @Result(property = "descripcion", column = "descripcion_sexo"),
            @Result(property = "estado", column = "fl_estado")
    })
    @Select("SELECT * FROM \"Admision\".tc_sexo WHERE fl_estado = '1'")
    List<Sexo> getAll();

    @ResultMap("sexoResultMap")
    @Select("SELECT * FROM \"Admision\".tc_sexo WHERE id_sexo = #{idSexo}")
    Sexo getSexoById(@Param("idSexo") Integer idSexo);

}
