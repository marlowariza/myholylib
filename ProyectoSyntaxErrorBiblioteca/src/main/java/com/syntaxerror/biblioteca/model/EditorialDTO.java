package com.syntaxerror.biblioteca.model;

public class EditorialDTO{
    private Integer idEditorial;
    private String nombre;
    private String sitioweb;
    private String pais;

    public EditorialDTO() {
        this.idEditorial = null;
        this.nombre = null;
        this.sitioweb = null;
        this.pais = null;
    }
    public EditorialDTO(Integer idEditorial, String nombre, String sitioweb, String pais) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.sitioweb = sitioweb;
        this.pais = pais;
        }
    public EditorialDTO(EditorialDTO editorial) {
        this.idEditorial = editorial.idEditorial;
        this.nombre = editorial.nombre;
        this.sitioweb = editorial.sitioweb;
        this.pais = editorial.pais;
    }
    public Integer getIdEditorial() {
        return idEditorial;
    }
    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSitioweb() {
        return sitioweb;
    }
    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
}
