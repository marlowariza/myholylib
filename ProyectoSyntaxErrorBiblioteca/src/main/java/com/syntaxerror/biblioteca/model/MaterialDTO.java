package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.PublicoObjetivo;
import java.util.ArrayList;
import java.util.Date;

public class MaterialDTO {

    private Integer idMaterial;
    private String titulo;
    private String estado;
    private String edicion;
    private Date fechaIngreso;
    private Date fechaPublicacion;
    private Integer cantidadEjemplares;
    private PublicoObjetivo lectorObjetivo;
    private String idioma;
    private ArrayList<PrestamoDTO> prestamos;
    private ArrayList<AutorDTO> autores;
    private ArrayList<SedeDTO> sedes;

    // Constructores
    public MaterialDTO() {
        this.idMaterial = null;
        this.titulo = null;
        this.edicion = null;
        this.estado = null;
        this.fechaIngreso = null;
        this.fechaPublicacion = null;
        this.cantidadEjemplares = null;
        this.idioma = null;
        this.prestamos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.sedes = new ArrayList<>();
    }

    public MaterialDTO(Integer idMaterial, String titulo, String estado, String edicion, Date fechaIngreso,
            Date fechaPublicacion, Integer cantidadEjemplares, PublicoObjetivo lectorObjetivo, String idioma) {
        this.idMaterial = idMaterial;
        this.titulo = titulo;
        this.estado = estado;
        this.edicion = edicion;
        this.fechaIngreso = fechaIngreso;
        this.fechaPublicacion = fechaPublicacion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.lectorObjetivo = lectorObjetivo;
        this.idioma = idioma;
        this.prestamos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.sedes = new ArrayList<>();
    }

    public MaterialDTO(MaterialDTO material) {
        this.idMaterial = material.idMaterial;
        this.titulo = material.titulo;
        this.estado = material.estado;
        this.edicion = material.edicion;
        this.fechaIngreso = material.fechaIngreso;
        this.fechaPublicacion = material.fechaPublicacion;
        this.cantidadEjemplares = material.cantidadEjemplares;
        this.lectorObjetivo = material.lectorObjetivo;
        this.idioma = material.idioma;
        this.prestamos = new ArrayList<>(material.prestamos);
        this.autores = new ArrayList<>(material.autores);
    }

    // Getters y Setters
    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(Integer cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public PublicoObjetivo getLectorObjetivo() {
        return lectorObjetivo;
    }

    public void setLectorObjetivo(PublicoObjetivo lectorObjetivo) {
        this.lectorObjetivo = lectorObjetivo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void agregarAutor(AutorDTO autor) {
        if (!autores.contains(autor)) {
            autores.add(autor);
        }
    }

    public void agregarSede(SedeDTO sede) {
        if (!sedes.contains(sede)) {
            sedes.add(sede);
            sede.agregarMaterial(this); // relación bidireccional
        }
    }

    public void quitarSede(SedeDTO sede) {
        if (sedes.contains(sede)) {
            sedes.remove(sede);
            sede.quitarMaterial(this);
        }
    }

//    public void agregarPrestamo(PrestamoDTO prestamo) {
//        if (prestamo != null && !prestamos.contains(prestamo)) {
//            prestamos.add(prestamo);
//            prestamo.agregarMaterial(this); // Establecemos la relación bidireccional
//        }
//    }
//
//    public void quitarPrestamo(PrestamoDTO prestamo) {
//        if (prestamo != null && prestamos.contains(prestamo)) {
//            prestamos.remove(prestamo);
//            prestamo.quitarMaterial(this); // Eliminamos la relación en ambos sentidos
//        }
//    }

}
