package com.syntaxerror.biblioteca.model;

public class SedeDTO {

    private Integer idSede;
    private String nombre;
    private String direccion;
    private String distrito;
    private String telefono_contacto;
    private String correo_contacto;

    public SedeDTO() {
        this.idSede = null;
        this.nombre = null;
        this.direccion = null;
        this.distrito = null;
        this.telefono_contacto = null;
        this.correo_contacto = null;
    }

    public SedeDTO(Integer idSede, String nombre, String direccion, String distrito,
            String telefono_contacto, String correo_contacto) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.telefono_contacto = telefono_contacto;
        this.correo_contacto = correo_contacto;
    }

    public SedeDTO(SedeDTO otraSede) {
        this.idSede = otraSede.idSede;
        this.nombre = otraSede.nombre;
        this.direccion = otraSede.direccion;
        this.distrito = otraSede.distrito;
        this.telefono_contacto = otraSede.telefono_contacto;
        this.correo_contacto = otraSede.correo_contacto;
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
}
