package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.EjemplarDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.enums.FormatoDigital;
import com.syntaxerror.biblioteca.model.enums.TipoEjemplar;
import com.syntaxerror.biblioteca.persistance.dao.EjemplarDAO;
import com.syntaxerror.biblioteca.persistance.dao.MaterialDAO;
import com.syntaxerror.biblioteca.persistance.dao.SedeDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.EjemplarDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import java.util.ArrayList;
import java.util.Date;

public class EjemplarBO {

    private EjemplarDAO ejemplarDAO;
    private SedeDAO sedeDAO;
    private MaterialDAO materialDAO;

    public EjemplarBO() {
        this.ejemplarDAO = new EjemplarDAOImpl();
        this.sedeDAO = new SedeDAOImpl();
        this.materialDAO = new MaterialDAOImpl();
    }

    public int insertar(Date fechaAdquisicion, Boolean disponible, TipoEjemplar tipo,
            FormatoDigital formatoDigital, String ubicacion,
            Integer idSede, Integer idMaterial) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        ejemplar.setFechaAdquisicion(fechaAdquisicion);
        ejemplar.setDisponible(disponible);
        ejemplar.setTipo(tipo);
        ejemplar.setFormatoDigital(formatoDigital);
        ejemplar.setUbicacion(ubicacion);

        SedeDTO sede = this.sedeDAO.obtenerPorId(idSede);
        sede.setIdSede(idSede);
        
        ejemplar.setSede(sede);

        MaterialDTO material = materialDAO.obtenerPorId(idMaterial);
        material.setIdMaterial(idMaterial);
        
        ejemplar.setMaterial(material);

        return this.ejemplarDAO.insertar(ejemplar);
    }

    public int modificar(Integer idEjemplar, Date fechaAdquisicion, Boolean disponible,
            TipoEjemplar tipo, FormatoDigital formatoDigital, String ubicacion,
            Integer idSede, Integer idMaterial) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        ejemplar.setIdEjemplar(idEjemplar);
        ejemplar.setFechaAdquisicion(fechaAdquisicion);
        ejemplar.setDisponible(disponible);
        ejemplar.setTipo(tipo);
        ejemplar.setFormatoDigital(formatoDigital);
        ejemplar.setUbicacion(ubicacion);

        SedeDTO sede = this.sedeDAO.obtenerPorId(idSede);
        sede.setIdSede(idSede);
        
        ejemplar.setSede(sede);

        MaterialDTO material = materialDAO.obtenerPorId(idMaterial);
        material.setIdMaterial(idMaterial);
        
        ejemplar.setMaterial(material);

        return this.ejemplarDAO.modificar(ejemplar);
    }

    public int eliminar(Integer idEjemplar) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        ejemplar.setIdEjemplar(idEjemplar);
        return this.ejemplarDAO.eliminar(ejemplar);
    }

    public EjemplarDTO obtenerPorId(Integer idEjemplar) {
        return this.ejemplarDAO.obtenerPorId(idEjemplar);
    }

    public ArrayList<EjemplarDTO> listarTodos() {
        return this.ejemplarDAO.listarTodos();
    }
}
