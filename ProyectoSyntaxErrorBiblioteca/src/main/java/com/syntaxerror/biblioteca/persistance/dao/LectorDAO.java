package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.LectorDTO;
import java.util.ArrayList;

public interface LectorDAO {

    public Integer insertar(LectorDTO lector);

    public LectorDTO obtenerPorId(Integer id);

    public Integer modificar(LectorDTO lector);

    public Integer eliminar(LectorDTO lector);

    public ArrayList<LectorDTO> listarTodos();
}
