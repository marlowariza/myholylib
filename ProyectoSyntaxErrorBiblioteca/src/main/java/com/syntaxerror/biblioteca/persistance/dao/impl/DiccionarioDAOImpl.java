package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.DiccionarioDTO;
import com.syntaxerror.biblioteca.persistance.dao.DiccionarioDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.util.ArrayList;

public class DiccionarioDAOImpl extends DAOImplBase implements DiccionarioDAO {

    public DiccionarioDAOImpl(String nombre_tabla) {
        super(nombre_tabla);
    }

    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas.add(new Columna("NumeroEntradas", false, false));
        this.listaColumnas.add(new Columna("ConImagenes", false, false));
        this.listaColumnas.add(new Columna("TipoIdioma", false, false));
        this.listaColumnas.add(new Columna("Material_IdMaterial", false, false)); // Relación con Material
    }

    @Override
    public Integer insertar(DiccionarioDTO lector) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DiccionarioDTO obtenerPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer modificar(DiccionarioDTO lector) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer eliminar(DiccionarioDTO lector) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DiccionarioDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
