package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.VocabularioDTO;
import java.util.ArrayList;

public interface VocabularioDAO {

    public Integer insertar(VocabularioDTO lector);

    public VocabularioDTO obtenerPorId(Integer id);

    public Integer modificar(VocabularioDTO lector);

    public Integer eliminar(VocabularioDTO lector);

    public ArrayList<VocabularioDTO> listarTodos();
}
