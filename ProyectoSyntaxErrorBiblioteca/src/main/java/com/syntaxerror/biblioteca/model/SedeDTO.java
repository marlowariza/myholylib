package com.syntaxerror.biblioteca.model;

import java.util.ArrayList;

public class SedeDTO {

    private Integer idSede;
    private String nombre;
    private String direccion;
    private String distrito;
    private String telefono_contacto;
    private String correo_contacto;

    private Boolean activa;

    private ArrayList<EjemplarDTO> ejemplares;
    private ArrayList<PersonaDTO> personas;

    public SedeDTO() {
        this.idSede = null;
        this.nombre = null;
        this.direccion = null;
        this.distrito = null;
        this.telefono_contacto = null;
        this.correo_contacto = null;
        this.activa = null;

        this.ejemplares = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public SedeDTO(Integer idSede, String nombre, String direccion, String distrito,
            String telefono_contacto, String correo_contacto, Boolean activa) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.telefono_contacto = telefono_contacto;
        this.correo_contacto = correo_contacto;
        this.activa = activa;

        this.ejemplares = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public SedeDTO(SedeDTO otraSede) {
        this.idSede = otraSede.idSede;
        this.nombre = otraSede.nombre;
        this.direccion = otraSede.direccion;
        this.distrito = otraSede.distrito;
        this.telefono_contacto = otraSede.telefono_contacto;
        this.correo_contacto = otraSede.correo_contacto;
        this.activa = otraSede.activa;

        this.ejemplares = otraSede.ejemplares != null ? new ArrayList<>(otraSede.ejemplares) : new ArrayList<>();
        this.personas = otraSede.personas != null ? new ArrayList<>(otraSede.personas) : new ArrayList<>();
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

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public String getCorreo_contacto() {
        return correo_contacto;
    }

    public void setCorreo_contacto(String correo_contacto) {
        this.correo_contacto = correo_contacto;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public void agregarEjemplar(EjemplarDTO m) {
        if (!ejemplares.contains(m)) {
            ejemplares.add(m);
        }
    }

    public void quitarEjemplar(EjemplarDTO m) {
        if (ejemplares.contains(m)) {
            ejemplares.remove(m);
        }
    }

    public void agregarPersona(PersonaDTO persona) {
        if (personas != null) {
            this.personas.add(persona);
        }
    }
}
