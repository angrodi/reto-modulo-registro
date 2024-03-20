CREATE OR REPLACE FUNCTION "Admision".fn_insertar_paciente(
    p_paciente_id_tipo_docide integer,
    p_paciente_no_docide character varying,
    p_paciente_no_apepat character varying,
    p_paciente_no_apemat character varying,
    p_paciente_no_nombres character varying,
    p_paciente_id_sexo integer,
    p_paciente_fe_nacimiento date,
    p_paciente_no_lugar_nacimiento character varying,
    p_paciente_no_direccion character varying,
    p_paciente_co_ubigeo character varying(6)
)
RETURNS integer AS $$
DECLARE
    paciente_id INTEGER;
BEGIN
    -- Insertar registro en la tabla tb_paciente
    INSERT INTO "Admision".tb_paciente (
        id_tipo_docide,
        no_docide,
        no_apepat,
        no_apemat,
        no_nombres,
        id_sexo,
        fe_nacimiento,
        no_lugar_nacimiento,
        no_direccion,
        co_ubigeo
    ) VALUES (
        p_paciente_id_tipo_docide,
        p_paciente_no_docide,
        p_paciente_no_apepat,
        p_paciente_no_apemat,
        p_paciente_no_nombres,
        p_paciente_id_sexo,
        p_paciente_fe_nacimiento,
        p_paciente_no_lugar_nacimiento,
        p_paciente_no_direccion,
        p_paciente_co_ubigeo
    ) RETURNING id_paciente INTO paciente_id;

    RETURN paciente_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "Admision".fn_insertar_paciente_acompanante(
    p_acompanante_id_paciente integer,
    p_acompanante_id_tipo_docide integer,
    p_acompanante_no_docide character varying,
    p_acompanante_no_apepat character varying,
    p_acompanante_no_apemat character varying,
    p_acompanante_no_nombres character varying,
    p_acompanante_fe_nacimiento date,
    p_acompanante_id_parentesco integer,
    p_acompanante_nu_telefo_contacto character varying,
    p_acompanante_no_direccion character varying,
    p_acompanante_co_ubigeo character varying(6)
)
RETURNS integer AS $$
DECLARE
    paciente_acompanante_id INTEGER;
BEGIN
    -- Insertar registro en la tabla tb_paciente_acompanante
    INSERT INTO "Admision".tb_paciente_acompanante (
        id_paciente,
        id_tipo_docide,
        no_docide,
        no_apepat,
        no_apemat,
        no_nombres,
        fe_nacimiento,
        id_parentesco,
        nu_telefo_contacto,
        no_direccion,
        co_ubigeo
    ) VALUES (
        p_acompanante_id_paciente,
        p_acompanante_id_tipo_docide,
        p_acompanante_no_docide,
        p_acompanante_no_apepat,
        p_acompanante_no_apemat,
        p_acompanante_no_nombres,
        p_acompanante_fe_nacimiento,
        p_acompanante_id_parentesco,
        p_acompanante_nu_telefo_contacto,
        p_acompanante_no_direccion,
        p_acompanante_co_ubigeo
    ) RETURNING id_paciente_acompanante INTO paciente_acompanante_id;

    RETURN paciente_acompanante_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "Admision".fn_insertar_paciente_y_acompanante(
    p_paciente_id_tipo_docide integer,
    p_paciente_no_docide character varying,
    p_paciente_no_apepat character varying,
    p_paciente_no_apemat character varying,
    p_paciente_no_nombres character varying,
    p_paciente_id_sexo integer,
    p_paciente_fe_nacimiento date,
    p_paciente_no_lugar_nacimiento character varying,
    p_paciente_no_direccion character varying,
    p_paciente_co_ubigeo character varying(6),
    
    p_acompanante_id_tipo_docide integer,
    p_acompanante_no_docide character varying,
    p_acompanante_no_apepat character varying,
    p_acompanante_no_apemat character varying,
    p_acompanante_no_nombres character varying,
    p_acompanante_fe_nacimiento date,
    p_acompanante_id_parentesco integer,
    p_acompanante_nu_telefo_contacto character varying,
    p_acompanante_no_direccion character varying,
    p_acompanante_co_ubigeo character varying(6)
)
RETURNS integer AS $$
DECLARE
    paciente_id INTEGER;
BEGIN
    -- Insertar registro en la tabla tb_paciente
    INSERT INTO "Admision".tb_paciente (
        id_tipo_docide,
        no_docide,
        no_apepat,
        no_apemat,
        no_nombres,
        id_sexo,
        fe_nacimiento,
        no_lugar_nacimiento,
        no_direccion,
        co_ubigeo
    ) VALUES (
        p_paciente_id_tipo_docide,
        p_paciente_no_docide,
        p_paciente_no_apepat,
        p_paciente_no_apemat,
        p_paciente_no_nombres,
        p_paciente_id_sexo,
        p_paciente_fe_nacimiento,
        p_paciente_no_lugar_nacimiento,
        p_paciente_no_direccion,
        p_paciente_co_ubigeo
    ) RETURNING id_paciente INTO paciente_id;

    -- Insertar registro en la tabla tb_paciente_acompanante
    INSERT INTO "Admision".tb_paciente_acompanante (
        id_paciente,
        id_tipo_docide,
        no_docide,
        no_apepat,
        no_apemat,
        no_nombres,
        fe_nacimiento,
        id_parentesco,
        nu_telefo_contacto,
        no_direccion,
        co_ubigeo
    ) VALUES (
        paciente_id,
        p_acompanante_id_tipo_docide,
        p_acompanante_no_docide,
        p_acompanante_no_apepat,
        p_acompanante_no_apemat,
        p_acompanante_no_nombres,
        p_acompanante_fe_nacimiento,
        p_acompanante_id_parentesco,
        p_acompanante_nu_telefo_contacto,
        p_acompanante_no_direccion,
        p_acompanante_co_ubigeo
    );

    RETURN paciente_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "Admision".fn_eliminar_paciente_y_acompanante(
    p_id_paciente integer
)
RETURNS boolean AS $$
DECLARE
    paciente_deleted boolean := false;
BEGIN
    -- Eliminar el registro de tb_paciente_acompanante
    DELETE FROM "Admision".tb_paciente_acompanante WHERE id_paciente = p_id_paciente;

    -- Eliminar el registro de tb_paciente
    DELETE FROM "Admision".tb_paciente WHERE id_paciente = p_id_paciente;
    
    -- Verificar si se eliminó correctamente el paciente
    IF NOT FOUND THEN
        paciente_deleted := false;
    ELSE
        paciente_deleted := true;
    END IF;

    RETURN paciente_deleted;
END;
$$ LANGUAGE plpgsql;