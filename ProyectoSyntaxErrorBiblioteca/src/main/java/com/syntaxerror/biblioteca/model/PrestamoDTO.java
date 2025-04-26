package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.EstadoPrestamo;
import java.util.ArrayList;
import java.util.Date;

public class PrestamoDTO {

    private String idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaRealDevolucion;
    private EstadoPrestamo estado;
    private LectorDTO lector;

    private MaterialDTO material;

    // Constructores
    public PrestamoDTO() {
        this.idPrestamo = null;
        this.fechaDevolucion = null;
        this.fechaPrestamo = null;
        this.fechaRealDevolucion = null;
        this.material = null;
    }

    public PrestamoDTO(String idPrestamo, Date fechaPrestamo, Date fechaDevolucion, Date fechaRealDevolucion, EstadoPrestamo estado, LectorDTO lector, MaterialDTO material) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaRealDevolucion = fechaRealDevolucion;
        this.estado = estado;
        this.lector = lector;
        this.material = material;
    }

    public PrestamoDTO(PrestamoDTO prestamo) {
        this.idPrestamo = prestamo.idPrestamo;
        this.fechaPrestamo = prestamo.fechaPrestamo;
        this.fechaDevolucion = prestamo.fechaDevolucion;
        this.fechaRealDevolucion = prestamo.fechaRealDevolucion;
        this.estado = prestamo.estado;
        this.lector = prestamo.lector;
        this.material = prestamo.material;
    }

    // Getters y Setters
    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaRealDevolucion() {
        return fechaRealDevolucion;
    }

    public void setFechaRealDevolucion(Date fechaRealDevolucion) {
        this.fechaRealDevolucion = fechaRealDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public LectorDTO getLector() {
        return lector;
    }

    public void setLector(LectorDTO lector) {
        this.lector = lector;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }

//    public void agregarMaterial(MaterialDTO material) {
//        if (material != null && !materiales.contains(material)) {
//            materiales.add(material);
//            material.agregarPrestamo(this); // Aseguramos la bidireccionalidad
//        }
//    }
//
//    public void quitarMaterial(MaterialDTO material) {
//        if (material != null && materiales.contains(material)) {
//            materiales.remove(material);
//            material.quitarPrestamo(this); // Eliminamos la relación en ambos sentidos
//        }
//    }
}
