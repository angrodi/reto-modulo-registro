package pe.tintegro.apppacientesbackend.dao;

import org.apache.ibatis.annotations.*;
import pe.tintegro.apppacientesbackend.dto.UbigeoDescCodDto;
import pe.tintegro.apppacientesbackend.model.Ubigeo;

import java.util.List;

@Mapper
public interface UbigeoMapper {

    @Results({
            @Result(property = "codigo", column = "codigo_ubigeo"),
            @Result(property = "descDepartamento", column = "descripcion_departamento"),
            @Result(property = "descProvincia", column = "descripcion_provincia"),
            @Result(property = "descDistrito", column = "descripcion_distrito"),
            @Result(property = "codigoDepartamento", column = "codigo_departamento"),
            @Result(property = "codigoProvincia", column = "codigo_provincia"),
            @Result(property = "codigoDistrito", column = "codigo_distrito"),
            @Result(property = "estado", column = "fl_estado")
    })
    @Select("SELECT * FROM \"Admision\".tc_ubigeo WHERE codigo_ubigeo = #{codigoUbigeo}")
    Ubigeo getUbigeoById(@Param("codigoUbigeo") String codigoUbigeo);

    @Results({
            @Result(property = "descripcion", column = "descripcion_departamento"),
            @Result(property = "codigo", column = "codigo_departamento")
    })
    @Select("SELECT DISTINCT descripcion_departamento, codigo_departamento " +
            "FROM \"Admision\".tc_ubigeo WHERE fl_estado = '1' ORDER BY descripcion_departamento")
    List<UbigeoDescCodDto> getDepartamentos();

    @Results({
            @Result(property = "descripcion", column = "descripcion_provincia"),
            @Result(property = "codigo", column = "codigo_provincia")
    })
    @Select("SELECT DISTINCT descripcion_provincia, codigo_provincia FROM \"Admision\".tc_ubigeo " +
            "WHERE codigo_departamento = #{codigoDepartamento} AND codigo_distrito != '00' AND fl_estado = '1' " +
            "ORDER BY descripcion_provincia")
    List<UbigeoDescCodDto> getProvincias(@Param("codigoDepartamento") String codigoDepartamento);

    @Results({
            @Result(property = "descripcion", column = "descripcion_distrito"),
            @Result(property = "codigo", column = "codigo_distrito")
    })
    @Select("SELECT descripcion_distrito, codigo_distrito FROM \"Admision\".tc_ubigeo " +
            "WHERE codigo_departamento = #{codigoDepartamento} AND codigo_provincia = #{codigoProvincia} AND descripcion_distrito != '' AND fl_estado = '1' " +
            "ORDER BY descripcion_distrito")
    List<UbigeoDescCodDto> getDistritos(@Param("codigoDepartamento") String codigoDepartamento,
                                        @Param("codigoProvincia") String codigoProvincia);

}
