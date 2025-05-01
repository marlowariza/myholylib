package com.syntaxerror.biblioteca.model;


public class AutorDTO {

    private Integer idAutor;
    private String nombre;
    private String nacionalidad;
    private Boolean activo;
    private Integer cantidadObras;

    // Constructores
    public AutorDTO() {
        this.idAutor = null;
        this.nombre = null;
        this.nacionalidad = null;
        this.activo = null;
        this.cantidadObras = null;
    }

    public AutorDTO(Integer idAutor, String nombre, String nacionalidad, Boolean activo, Integer cantidadObras) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.activo = activo;
        this.cantidadObras = cantidadObras;
    }

    public AutorDTO(AutorDTO autor) {
        this.idAutor = autor.idAutor;
        this.nombre = autor.nombre;
        this.nacionalidad = autor.nacionalidad;
        this.activo = autor.activo;
        this.cantidadObras = autor.cantidadObras;
    }

    // Getters y Setters
    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getCantidadObras() {
        return cantidadObras;
    }

    public void setCantidadObras(Integer cantidadObras) {
        this.cantidadObras = cantidadObras;
    }
}
