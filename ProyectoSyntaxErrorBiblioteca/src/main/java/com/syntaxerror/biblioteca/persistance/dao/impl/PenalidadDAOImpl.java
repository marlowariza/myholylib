package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.PenalidadDTO;
import com.syntaxerror.biblioteca.model.enums.TipoPenalidad;
import com.syntaxerror.biblioteca.persistance.dao.PenalidadDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PenalidadDAOImpl extends DAOImplBase implements PenalidadDAO {

    private PenalidadDTO penalidad;

    public PenalidadDAOImpl() {
        super("Penalidad");
        this.retornarLlavePrimaria = true;
        this.penalidad = null;
    }

    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas.add(new Columna("idPenalidad", true, false));
        this.listaColumnas.add(new Columna("Fecha", false, false));
        this.listaColumnas.add(new Columna("Monto", false, false));
        this.listaColumnas.add(new Columna("Descripcion", false, false));
        this.listaColumnas.add(new Columna("Tipo", false, false));
        //this.listaColumnas.add(new Columna("PenalidadCol", false, false));
    }

    @Override
    public Integer insertar(PenalidadDTO penalidad) {
        this.penalidad = penalidad;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setInt(1, this.penalidad.getIdPenalidad());
            this.statement.setDate(2, new java.sql.Date(this.penalidad.getFecha().getTime()));
            this.statement.setDouble(3, this.penalidad.getMonto());
            this.statement.setString(4, this.penalidad.getDescripcion()); 
            this.statement.setString(5, this.penalidad.getTipo().name()); 
        } catch (SQLException ex) {
            Logger.getLogger(PenalidadDAOImpl.class.getName()).log(Level.SEVERE, "Error al asignar valores al statement", ex);
        }
    }

    @Override
    public PenalidadDTO obtenerPorId(Integer idPenalidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PenalidadDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer modificar(PenalidadDTO penalidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer eliminar(Integer idPenalidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
