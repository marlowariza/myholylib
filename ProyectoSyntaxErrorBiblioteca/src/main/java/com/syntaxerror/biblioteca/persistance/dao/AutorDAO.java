package com.syntaxerror.biblioteca.persistance.dao;

import java.util.ArrayList;

import com.syntaxerror.biblioteca.model.AutorDTO;

public interface AutorDAO {
    public Integer insertar(AutorDTO autor);

    public AutorDTO obtenerPorId(Integer autorId);

    public ArrayList<AutorDTO> listarTodos();

    public Integer modificar(AutorDTO autor);

    public Integer eliminar(AutorDTO autor);
}