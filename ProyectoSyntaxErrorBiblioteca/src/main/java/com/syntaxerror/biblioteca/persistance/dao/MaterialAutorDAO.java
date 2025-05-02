package com.syntaxerror.biblioteca.persistance.dao;

import java.util.List;

public interface MaterialAutorDAO {
    public Integer asociarMaterialAutor(Integer idMaterial, Integer idAutor);

    public Integer desasociarMaterialAutor(Integer idMaterial, Integer idAutor);

    public boolean existeAsociacion(Integer idMaterial, Integer idAutor);

    public List<Integer> buscarAutoresPorMaterial(Integer idMaterial);

    public List<Integer> buscarMaterialesPorAutor(Integer idAutor);
}
