/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.PersonaDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.model.enums.TipoPersona;
import com.syntaxerror.biblioteca.model.enums.Turnos;
import com.syntaxerror.biblioteca.persistance.dao.PersonaDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.PersonaDAOImpl;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author aml
 */
public class PersonaBO {
    private PersonaDAO personaDAO;

    public PersonaBO() {
        this.personaDAO= new PersonaDAOImpl();
    }
    
    public int insertar(String nombre, String paterno, String materno, String direccion,
            String telefono, String correo, String contrasenha, TipoPersona tipo, NivelDeIngles nivel,
            Turnos turno, Date fechaContratoInicio, Date fechaContratoFinal, Boolean vigente, SedeDTO sede) {
        PersonaDTO persona = new PersonaDTO();

        persona.setNombre(nombre);
        persona.setPaterno(paterno);
        persona.setMaterno(materno);
        persona.setDireccion(direccion);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setContrasenha(contrasenha);
        persona.setTipo(tipo);
        persona.setNivel(nivel);
        persona.setTurno(turno);
        persona.setFechaContratoInicio(fechaContratoInicio);
        persona.setFechaContratoFinal(fechaContratoFinal);
        persona.setVigente(vigente);
        
        persona.setSede(sede);

        return this.personaDAO.insertar(persona);
    }
    
    
    

    public int modificar(Integer idPersona, String nombre, String paterno, String materno, String direccion,
            String telefono, String correo, String contrasenha, TipoPersona tipo, NivelDeIngles nivel,
            Turnos turno, Date fechaContratoInicio, Date fechaContratoFinal, Boolean vigente, SedeDTO sede) {
        PersonaDTO persona = new PersonaDTO();
        persona.setIdPersona(idPersona);

        persona.setNombre(nombre);
        persona.setPaterno(paterno);
        persona.setMaterno(materno);
        persona.setDireccion(direccion);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setContrasenha(contrasenha);
        persona.setTipo(tipo);
        persona.setNivel(nivel);
        persona.setTurno(turno);
        persona.setFechaContratoInicio(fechaContratoInicio);
        persona.setFechaContratoFinal(fechaContratoFinal);
        persona.setVigente(vigente);
        
        persona.setSede(sede);
        return this.personaDAO.modificar(persona);
    }

    public int eliminar(Integer idPersona) {
        PersonaDTO persona = new PersonaDTO();
        persona.setIdPersona(idPersona);
        return this.personaDAO.eliminar(persona);
    }

    public PersonaDTO obtenerPorId(Integer idPrestamo) {
        return this.personaDAO.obtenerPorId(idPrestamo);
    }

    public ArrayList<PersonaDTO> listarTodos() {
        return this.personaDAO.listarTodos();
    }
}
