package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.SedeDTO;
import java.util.ArrayList;

/**
 *
 * @author Fabian
 */
public interface SedeDAO {

    public Integer insertar(SedeDTO autor);

    public SedeDTO obtenerPorId(Integer autorId);

    public ArrayList<SedeDTO> listarTodos();

    public Integer modificar(SedeDTO autor);

    public Integer eliminar(SedeDTO autor);
}
