package com.syntaxerror.biblioteca.model;

import java.util.ArrayList;

public class AutorDTO {

    private Integer idAutor;
    private String nombre;
    private String nacionalidad;
    private Boolean activo;
    private Integer cantidadObras;
    private ArrayList<MaterialDTO> materiales;

    // Constructores
    public AutorDTO() {
        this.idAutor = null;
        this.nombre = null;
        this.nacionalidad = null;
        this.activo = null;
        this.cantidadObras = null;
        this.materiales = new ArrayList<>();
    }

    public AutorDTO(Integer idAutor, String nombre, String nacionalidad, Boolean activo, Integer cantidadObras) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.activo = activo;
        this.cantidadObras = cantidadObras;
        this.materiales = new ArrayList<>();
    }

    public AutorDTO(AutorDTO autor) {
        this.idAutor = autor.idAutor;
        this.nombre = autor.nombre;
        this.nacionalidad = autor.nacionalidad;
        this.activo = autor.activo;
        this.cantidadObras = autor.cantidadObras;
        this.materiales = new ArrayList<>(autor.materiales);
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

public void agregarMaterial(MaterialDTO material) {
    if (!this.materiales.contains(material)) {
        this.materiales.add(material);
        material.agregarAutor(this); // Relación bidireccional
    }
}
}
