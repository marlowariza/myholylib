
package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.PersonaDTO;
import com.syntaxerror.biblioteca.model.PrestamoDTO;
import com.syntaxerror.biblioteca.persistance.dao.PrestamoDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.PrestamoDAOImpl;
import java.util.ArrayList;
import java.util.Date;

public class PrestamoBO {

    private PrestamoDAO prestamoDAO;

    public PrestamoBO() {
        this.prestamoDAO = new PrestamoDAOImpl();
    }

    public int insertar(Date fechaSolicitud, Date fechaPrestamo, Date fechaDevolucion, Integer idPersona) {
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setFechaSolicitud(fechaSolicitud);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);

        PersonaDTO persona = new PersonaDTO();
        persona.setIdPersona(idPersona);
        prestamo.setPersona(persona);

        return this.prestamoDAO.insertar(prestamo);
    }

    public int modificar(Integer idPrestamo, Date fechaSolicitud, Date fechaPrestamo, Date fechaDevolucion, Integer idPersona) {
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setIdPrestamo(idPrestamo);
        prestamo.setFechaSolicitud(fechaSolicitud);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);

        PersonaDTO persona = new PersonaDTO();
        persona.setIdPersona(idPersona);
        prestamo.setPersona(persona);

        return this.prestamoDAO.modificar(prestamo);
    }

    public int eliminar(Integer idPrestamo) {
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setIdPrestamo(idPrestamo);
        return this.prestamoDAO.eliminar(prestamo);
    }

    public PrestamoDTO obtenerPorId(Integer idPrestamo) {
        return this.prestamoDAO.obtenerPorId(idPrestamo);
    }

    public ArrayList<PrestamoDTO> listarTodos() {
        return this.prestamoDAO.listarTodos();
    }
}

