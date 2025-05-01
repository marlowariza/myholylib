package com.syntaxerror.biblioteca.model;


public abstract class PersonaDTO {
    private Integer idPersona; 
    private String nombre;
    private String direccion;
    private String telefono; 
    private String correo;
    private String contrasenha;
    private SedeDTO sede;

    //Constructores
    public PersonaDTO(Integer idPersona, String nombre, String direccion,
                   String telefono, String correo, String contrasenha, SedeDTO sede) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.sede = sede;
    }
    
    public PersonaDTO(){
        this.idPersona=null;
        this.nombre=null;
        this.direccion=null;
        this.telefono=null;
        this.correo=null;
        this.contrasenha=null;
        this.sede=null;
    }
    
    public PersonaDTO(PersonaDTO persona) {
        this.idPersona = persona.idPersona;
        this.nombre = persona.nombre;
        this.direccion = persona.direccion;
        this.telefono = persona.telefono;
        this.correo = persona.correo;
        this.contrasenha = persona.contrasenha;
        this.sede = persona.sede;
    }

    //Setters y getters
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