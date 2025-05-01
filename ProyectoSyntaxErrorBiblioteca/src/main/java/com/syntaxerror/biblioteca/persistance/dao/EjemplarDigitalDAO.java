package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.EjemplarDigitalDTO;
import java.util.ArrayList;

public interface EjemplarDigitalDAO {

    public Integer insertar(EjemplarDigitalDTO ejemplarDigital);

    public EjemplarDigitalDTO obtenerPorId(Integer idEjemplarDigital);

    public ArrayList<EjemplarDigitalDTO> listarTodos();

    public Integer modificar(EjemplarDigitalDTO ejemplarDigital);

    public Integer eliminar(EjemplarDigitalDTO ejemplarDigital);
}
