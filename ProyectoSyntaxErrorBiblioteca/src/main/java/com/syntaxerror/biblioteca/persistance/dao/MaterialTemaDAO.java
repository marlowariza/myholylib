package com.syntaxerror.biblioteca.persistance.dao;

import java.util.List;

public interface MaterialTemaDAO {
    Integer asociarMaterialTema(Integer idMaterial, Integer idTema);

    Integer desasociarMaterialTema(Integer idMaterial, Integer idTema);

    boolean existeAsociacion(Integer idMaterial, Integer idTema);

    List<Integer> buscarTemasPorMaterial(Integer idMaterial);

    List<Integer> buscarMaterialesPorTema(Integer idTema);
}
