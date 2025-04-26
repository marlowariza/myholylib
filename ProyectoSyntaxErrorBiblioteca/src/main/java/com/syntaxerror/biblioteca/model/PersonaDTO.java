package com.syntaxerror.biblioteca.model;

import java.util.Date;


public abstract class PersonaDTO {
    private Integer idPersona; 
    private Date fechaNacimiento; 
    private String nombre;
    private String direccion;
    private String telefono; 
    private String email;
    private String contrasenha;
    
    //Constructores
    public PersonaDTO(Integer idPersona, Date fechaNacimiento, String nombre, String direccion,
                   String telefono, String email, String contrasenha) {
        this.idPersona = idPersona;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.contrasenha = contrasenha;
    }
    
    public PersonaDTO(){
        this.idPersona=null;
        this.fechaNacimiento=null;
        this.nombre=null;
        this.direccion=null;
        this.telefono=null;
        this.email=null;
        this.contrasenha=null;
    }
    
    public PersonaDTO(PersonaDTO persona) {
        this.idPersona = persona.idPersona;
        this.fechaNacimiento = persona.fechaNacimiento;
        this.nombre = persona.nombre;
        this.direccion = persona.direccion;
        this.telefono = persona.telefono;
        this.email = persona.email;
        this.contrasenha = persona.contrasenha;
    }

    //Setters y getters
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}
