package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.CuentoCortoDTO;
import java.util.ArrayList;

public interface CuentoCortoDAO {

    public Integer insertar(CuentoCortoDTO lector);

    public CuentoCortoDTO obtenerPorId(Integer id);

    public Integer modificar(CuentoCortoDTO lector);

    public Integer eliminar(CuentoCortoDTO lector);

    public ArrayList<CuentoCortoDTO> listarTodos();
}
