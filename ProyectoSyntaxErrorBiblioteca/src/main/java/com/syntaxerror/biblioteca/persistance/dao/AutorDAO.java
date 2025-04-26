
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.AutorDTO;
import java.util.ArrayList;


public interface AutorDAO {
    public Integer insertar(AutorDTO autor);
    public AutorDTO obtenerPorId(Integer id);
    public Integer modificar(AutorDTO autor);
    public Integer eliminar(AutorDTO autor);
    public ArrayList<AutorDTO> listarTodos();
}
