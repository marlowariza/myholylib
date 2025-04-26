package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.PrestamoDTO;
import com.syntaxerror.biblioteca.persistance.dao.PrestamoDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrestamoDAOImpl extends DAOImplBase implements PrestamoDAO {

    private PrestamoDTO prestamo;

    public PrestamoDAOImpl(String nombre_tabla) {
        super("Prestamo");
        this.retornarLlavePrimaria = true;
        this.prestamo = null;
    }

    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas.add(new Columna("idPrestamo", true, false));
        this.listaColumnas.add(new Columna("FechaPrestamo", false, false));
        this.listaColumnas.add(new Columna("FechaDevolucion", false, false));
        this.listaColumnas.add(new Columna("FechaRealDevolucion", false, false));
        this.listaColumnas.add(new Columna("Estado", false, false));
        this.listaColumnas.add(new Columna("Material_idMaterial", false, false));
        this.listaColumnas.add(new Columna("Lector_IdLector", false, false));
    }

    @Override
    public Integer insertar(PrestamoDTO prestamo) {
        this.prestamo = prestamo;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {

        try {

            this.statement.setString(1, this.prestamo.getIdPrestamo());
            this.statement.setDate(2, new java.sql.Date(this.prestamo.getFechaPrestamo().getTime()));
            this.statement.setDate(3, new java.sql.Date(this.prestamo.getFechaDevolucion().getTime()));
            this.statement.setObject(4, this.prestamo.getFechaRealDevolucion());
            this.statement.setString(5, this.prestamo.getEstado().name());
            this.statement.setInt(6,this.prestamo.getMaterial().getIdMaterial() );
            this.statement.setInt(7, this.prestamo.getLector().getIdPersona());

        } catch (SQLException ex) {
            Logger.getLogger(SedeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public PrestamoDTO obtenerPorId(Integer idPrestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PrestamoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer modificar(PrestamoDTO prestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer eliminar(Integer idPrestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
