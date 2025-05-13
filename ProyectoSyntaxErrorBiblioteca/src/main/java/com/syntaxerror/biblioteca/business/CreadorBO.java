/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.CreadorDTO;
import com.syntaxerror.biblioteca.model.enums.TipoAutor;
import com.syntaxerror.biblioteca.persistance.dao.CreadorDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.CreadorDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author catolica
 */
public class CreadorBO {
    private CreadorDAO creadorDAO;

    public CreadorBO() {
        this.creadorDAO = new CreadorDAOImpl();
    }

    public int insertar(String nombre, String paterno, String materno,
            String seudonimo, TipoAutor tipo, String nacionalidad, Boolean activo) {
        CreadorDTO creador = new CreadorDTO();
        creador.setNombre(nombre);
        creador.setPaterno(paterno);
        creador.setMaterno(materno);
        creador.setSeudonimo(seudonimo);
        creador.setTipo(tipo);
        creador.setNacionalidad(nacionalidad);
        creador.setActivo(activo);

        return this.creadorDAO.insertar(creador);
    }

    public int modificar(String nombre, String paterno, String materno,
            String seudonimo, TipoAutor tipo, String nacionalidad, Boolean activo) {
        CreadorDTO creador = new CreadorDTO();
        creador.setNombre(nombre);
        creador.setPaterno(paterno);
        creador.setMaterno(materno);
        creador.setSeudonimo(seudonimo);
        creador.setTipo(tipo);
        creador.setNacionalidad(nacionalidad);
        creador.setActivo(activo);

        return this.creadorDAO.modificar(creador);
    }

    public int eliminar(Integer idCreador) {
        CreadorDTO creador = new CreadorDTO();
        creador.setIdAutor(idCreador);
        return this.creadorDAO.eliminar(creador);
    }

    public CreadorDTO obtenerPorId(Integer idCreador) {
        return this.creadorDAO.obtenerPorId(idCreador);
    }

    public ArrayList<CreadorDTO> listarTodos() {
        return this.creadorDAO.listarTodos();
    }
}
