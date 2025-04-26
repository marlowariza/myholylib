package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.PenalidadDTO;
import java.util.ArrayList;

public interface PenalidadDAO {

    public Integer insertar(PenalidadDTO penalidad);

    public PenalidadDTO obtenerPorId(Integer idPenalidad);

    public ArrayList<PenalidadDTO> listarTodos();

    public Integer modificar(PenalidadDTO penalidad);

    public Integer eliminar(Integer idPenalidad);
}
