package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.model.Parentesco;

import java.util.List;

@Mapper
public interface ParentescoMapper {

    @Results(id = "parentescoResultMap", value = {
            @Result(property = "idParentesco", column = "id_parentesco"),
            @Result(property = "parentesco", column = "no_parentesco"),
            @Result(property = "activo", column = "il_activo")
    })
    @Select("SELECT * FROM \"Admision\".tc_parentesco WHERE il_activo = '1'")
    List<Parentesco> getAll();

    @ResultMap("parentescoResultMap")
    @Select("SELECT * FROM \"Admision\".tc_parentesco WHERE id_parentesco = #{idParentesco}")
    Parentesco getParentescoById(@Param("idParentesco") Integer idParentesco);

}
