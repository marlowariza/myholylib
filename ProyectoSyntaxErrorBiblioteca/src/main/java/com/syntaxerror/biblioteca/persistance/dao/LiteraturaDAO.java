package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.LiteraturaDTO;
import java.util.ArrayList;

public interface LiteraturaDAO {

    public Integer insertar(LiteraturaDTO lector);

    public LiteraturaDTO obtenerPorId(Integer id);

    public Integer modificar(LiteraturaDTO lector);

    public Integer eliminar(LiteraturaDTO lector);

    public ArrayList<LiteraturaDTO> listarTodos();
}
