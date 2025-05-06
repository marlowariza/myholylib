package com.syntaxerror.biblioteca.persistance.dao;

import java.util.ArrayList;
import com.syntaxerror.biblioteca.model.LectorDTO;

public interface LectorDAO {

    Integer insertar(LectorDTO lector);

    Integer modificar(LectorDTO lector);

    Integer eliminar(Integer idLector);

    LectorDTO obtenerPorId(Integer idLector);

    ArrayList<LectorDTO> listarTodos();
}
