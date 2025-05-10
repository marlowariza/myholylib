package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.PrestamoDTO;
import com.syntaxerror.biblioteca.model.enums.TipoSancion;
import com.syntaxerror.biblioteca.model.SancionDTO;
import com.syntaxerror.biblioteca.persistance.dao.SancionDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.SancionDAOImpl;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fabian
 */
public class SancionBO {

    private SancionDAO sancionDAO;

    public SancionBO() {
        this.sancionDAO = new SancionDAOImpl();
    }

    public int insertar(TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion, PrestamoDTO prestamo) {
        SancionDTO sancion = new SancionDTO();
        sancion.setTipo(tipo);
        sancion.setFecha(fecha);
        sancion.setMonto(monto);
        sancion.setDuracion(duracion);
        sancion.setDescripcion(descripcion);
        sancion.setPrestamo(prestamo);

        return this.sancionDAO.insertar(sancion);
    }

    public int modificar(Integer idSancion, TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion, PrestamoDTO prestamo) {
        SancionDTO sancion = new SancionDTO();
        sancion.setIdSancion(idSancion);
        sancion.setTipo(tipo);
        sancion.setFecha(fecha);
        sancion.setMonto(monto);
        sancion.setDuracion(duracion);
        sancion.setDescripcion(descripcion);
        sancion.setPrestamo(prestamo);

        return this.sancionDAO.modificar(sancion);
    }

    public int eliminar(Integer idSancion) {
        SancionDTO sancion = new SancionDTO();
        sancion.setIdSancion(idSancion);
        return this.sancionDAO.eliminar(sancion);
    }

    public SancionDTO obtenerPorId(Integer idSancion) {
        return this.sancionDAO.obtenerPorId(idSancion);
    }

    public ArrayList<SancionDTO> listarTodos() {
        return this.sancionDAO.listarTodos();
    }
}

