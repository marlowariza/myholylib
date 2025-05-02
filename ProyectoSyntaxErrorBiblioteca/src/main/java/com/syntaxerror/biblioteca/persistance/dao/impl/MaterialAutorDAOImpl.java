package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.util.List;

import com.syntaxerror.biblioteca.persistance.dao.MaterialAutorDAO;

public class MaterialAutorDAOImpl extends RelacionDAOImplBase implements MaterialAutorDAO {
    public MaterialAutorDAOImpl() {
        super("BIB_MATERIAL_AUTOR", "MATERIAL_IDMATERIAL", "AUTOR_IDAUTOR");
    }

    @Override
    public Integer asociarMaterialAutor(Integer idMaterial, Integer idAutor) {
        return crearRelacion(idMaterial, idAutor);
    }

    @Override
    public Integer desasociarMaterialAutor(Integer idMaterial, Integer idAutor) {
        return eliminarRelacion(idMaterial, idAutor);
    }

    @Override
    public boolean existeAsociacion(Integer idMaterial, Integer idAutor) {
        return existeRelacion(idMaterial, idAutor);
    }

    @Override
    public List<Integer> buscarAutoresPorMaterial(Integer idMaterial) {
        return buscarRelacionadosPorId1(idMaterial);
    }

    @Override
    public List<Integer> buscarMaterialesPorAutor(Integer idAutor) {
        return buscarRelacionadosPorId2(idAutor);
    }
}
