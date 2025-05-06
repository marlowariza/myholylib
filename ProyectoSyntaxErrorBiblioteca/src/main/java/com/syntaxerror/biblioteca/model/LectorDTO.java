package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;

public class LectorDTO {

    private Integer idPersona;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String contrasenha;
    private SedeDTO sede;
    private Integer idLector;
    private NivelDeIngles nivel;

    // Constructores
    public LectorDTO() {
        this.idPersona = null;
        this.nombre = null;
        this.direccion = null;
        this.telefono = null;
        this.correo = null;
        this.contrasenha = null;
        this.sede = null;
        this.idLector = null;
        this.nivel = null;
    }

    public LectorDTO(Integer idPersona, String nombre, String direccion,
            String telefono, String correo, String contrasenha, SedeDTO sede, Integer idLector, NivelDeIngles nivel) {
        //super(idPersona, nombre, direccion, telefono, correo, contrasenha, sede);
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.sede = sede;
        this.idLector = idLector;
        this.nivel = nivel;
    }
    //lo unico malo es que se repito codigo acá

    public LectorDTO(LectorDTO lector) {
        //super(lector);
        this.idPersona = lector.idPersona;
        this.nombre = lector.nombre;
        this.direccion = lector.direccion;
        this.telefono = lector.telefono;
        this.correo = lector.correo;
        this.contrasenha = lector.contrasenha;
        this.sede = lector.sede;
        this.idLector = lector.idLector;
        this.nivel = lector.nivel;
    }

    //Getters y setters
    public Integer getIdLector() {
        return idLector;
    }

    public void setIdLector(Integer idLector) {
        this.idLector = idLector;
    }

    public NivelDeIngles getNivel() {
        return nivel;
    }

    public void setNivel(NivelDeIngles nivel) {
        this.nivel = nivel;
    }
    
    
    
    
    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String email) {
        this.correo = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public void setSede(SedeDTO sede) {
        this.sede = sede;
    }
}
