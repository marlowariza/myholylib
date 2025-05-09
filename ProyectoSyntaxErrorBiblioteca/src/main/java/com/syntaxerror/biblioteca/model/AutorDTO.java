package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.TipoAutor;

public class AutorDTO {

    private Integer idAutor;
    private String nombre;
    private String paterno;
    private String materno;
    private String seudonimo;
    private TipoAutor tipo;
    private String nacionalidad;
    private Boolean activo;
    private Integer cantidadObras;

    // Constructores
    public AutorDTO() {
        this.idAutor = null;
        this.nombre = null;
        this.paterno = null;
        this.materno = null;
        this.seudonimo = null;
        this.tipo = null;
        this.nacionalidad = null;
        this.activo = null;
        this.cantidadObras = null;
    }

    public AutorDTO(Integer idAutor, String nombre, String paterno, String materno,
            String seudonimo, TipoAutor tipo, String nacionalidad, Boolean activo, Integer cantidadObras) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.seudonimo = seudonimo;
        this.tipo = tipo;
        this.nacionalidad = nacionalidad;
        this.activo = activo;
        this.cantidadObras = cantidadObras;
    }

    public AutorDTO(AutorDTO autor) {
        this.idAutor = autor.idAutor;
        this.nombre = autor.nombre;
        this.paterno = autor.paterno;
        this.materno = autor.materno;
        this.seudonimo = autor.seudonimo;
        this.tipo = autor.tipo;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    public TipoAutor getTipo() {
        return tipo;
    }

    public void setTipo(TipoAutor tipo) {
        this.tipo = tipo;
    }
}
