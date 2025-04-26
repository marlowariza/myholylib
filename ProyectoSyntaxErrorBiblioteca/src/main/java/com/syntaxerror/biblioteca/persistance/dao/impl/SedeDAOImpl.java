package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.PrestamoDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.persistance.dao.SedeDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SedeDAOImpl extends DAOImplBase implements SedeDAO {

    private SedeDTO sede;

    public SedeDAOImpl() {
        super("Sede");
        this.retornarLlavePrimaria = true;
        this.sede = null;
    }

    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas.add(new Columna("IdSede", true, false));
        this.listaColumnas.add(new Columna("Nombre", false, false));
        this.listaColumnas.add(new Columna("Direccion", false, false));
        this.listaColumnas.add(new Columna("Distrito", false, false));
        this.listaColumnas.add(new Columna("TelefonoContacto", false, false));
        this.listaColumnas.add(new Columna("CorreoContacto", false, false));
    }

    @Override
    public Integer insertar(SedeDTO sede) {
        this.sede = sede;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setInt(1, this.sede.getIdSede());
            this.statement.setString(2, this.sede.getNombre());
            this.statement.setString(3, this.sede.getDireccion());
            this.statement.setString(4, this.sede.getDistrito());
            this.statement.setString(5, this.sede.getTelefono_contacto());
            this.statement.setString(6, this.sede.getCorreo_contacto());
        } catch (SQLException ex) {
            Logger.getLogger(SedeDAOImpl.class.getName()).log(Level.SEVERE, "Error al asignar valores a los parámetros", ex);
        }
    }

    @Override
    public SedeDTO obtenerPorId(Integer idSede) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    @Override
//    public SedeDTO obtenerPorId(Integer idSede) {
//        this.sede = new SedeDTO(); // Inicializa el objeto DTO
//        this.sede.setIdSede(idSede); // Establece el ID a buscar
//
//        return this.generarSQLParaObtenerPorId();
//    }

    @Override
    public ArrayList<SedeDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer modificar(SedeDTO sede) {
        this.sede = sede;
        return super.modificar();
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {
            this.statement.setString(1, this.sede.getNombre());
            this.statement.setString(2, this.sede.getDireccion());
            this.statement.setString(3, this.sede.getDistrito());
            this.statement.setString(4, this.sede.getTelefono_contacto());
            this.statement.setString(5, this.sede.getCorreo_contacto());

            // Último parámetro: clave primaria usada en WHERE para modificar solo la sede correspondiente
            this.statement.setInt(6, this.sede.getIdSede());
        } catch (SQLException ex) {
            Logger.getLogger(SedeDAOImpl.class.getName()).log(Level.SEVERE, "Error al asignar valores para modificación", ex);
        }
    }

    @Override
    public Integer eliminar(Integer idSede) {
        this.sede = new SedeDTO();
        this.sede.setIdSede(idSede);

        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() {
        try {
            this.statement.setInt(1, this.sede.getIdSede());
        } catch (SQLException ex) {
            Logger.getLogger(SedeDAOImpl.class.getName()).log(Level.SEVERE, "Error al asignar parámetros para eliminación", ex);
        }
    }



}
