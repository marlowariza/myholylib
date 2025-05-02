package com.syntaxerror.biblioteca.persistance.dao;

import java.util.List;

import com.syntaxerror.biblioteca.model.PrestamoEjemplarDTO;

public interface PrestamoEjemplarDAO {
    Integer insertar(PrestamoEjemplarDTO prestamoEjemplar);

    Integer modificar(PrestamoEjemplarDTO prestamoEjemplar);

    Integer eliminar(Integer idPrestamo, Integer idEjemplar);

    PrestamoEjemplarDTO buscarPorIds(Integer idPrestamo, Integer idEjemplar);

    List<PrestamoEjemplarDTO> buscarPorPrestamo(Integer idPrestamo);

    List<PrestamoEjemplarDTO> buscarPorEjemplar(Integer idEjemplar);

    boolean existeRelacion(Integer idPrestamo, Integer idEjemplar);
}
