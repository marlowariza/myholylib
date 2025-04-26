package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.DiccionarioDTO;
import java.util.ArrayList;

public interface DiccionarioDAO {

    public Integer insertar(DiccionarioDTO lector);

    public DiccionarioDTO obtenerPorId(Integer id);

    public Integer modificar(DiccionarioDTO lector);

    public Integer eliminar(DiccionarioDTO lector);

    public ArrayList<DiccionarioDTO> listarTodos();
}
