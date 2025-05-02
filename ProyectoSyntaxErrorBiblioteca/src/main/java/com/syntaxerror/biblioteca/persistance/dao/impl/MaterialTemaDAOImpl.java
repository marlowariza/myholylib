package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.util.List;

import com.syntaxerror.biblioteca.persistance.dao.MaterialTemaDAO;

public class MaterialTemaDAOImpl extends RelacionDAOImplBase implements MaterialTemaDAO {
    public MaterialTemaDAOImpl() {
        super("BIB_MATERIAL_TEMA", "MATERIAL_IDMATERIAL", "TEMA_IDTEMA");
    }

    @Override
    public Integer asociarMaterialTema(Integer materialId, Integer temaId) {
        return crearRelacion(materialId, temaId);
    }

    @Override
    public Integer desasociarMaterialTema(Integer materialId, Integer temaId) {
        return eliminarRelacion(materialId, temaId);
    }

    @Override
    public boolean existeAsociacion(Integer materialId, Integer temaId) {
        return existeRelacion(materialId, temaId);
    }

    @Override
    public List<Integer> buscarTemasPorMaterial(Integer materialId) {
        return buscarRelacionadosPorId1(materialId);
    }

    @Override
    public List<Integer> buscarMaterialesPorTema(Integer temaId) {
        return buscarRelacionadosPorId2(temaId);
    }
}
