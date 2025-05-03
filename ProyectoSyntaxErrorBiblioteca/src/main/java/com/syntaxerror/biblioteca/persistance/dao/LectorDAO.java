package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.LectorDTO;
import java.util.ArrayList;

public interface LectorDAO {

    Integer insertar(LectorDTO lector);

    Integer modificar(LectorDTO lector);

    Integer eliminar(LectorDTO lector);

    LectorDTO obtenerPorId(Integer idLector);

    ArrayList<LectorDTO> listarTodos();
}
