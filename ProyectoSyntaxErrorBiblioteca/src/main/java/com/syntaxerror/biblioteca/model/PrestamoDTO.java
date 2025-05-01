package com.syntaxerror.biblioteca.model;

import java.util.Date;

public class PrestamoDTO {

    private Integer idPrestamo;
    private Date fechaSolicitud;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private LectorDTO lector;

    // Constructores
    public PrestamoDTO() {
        this.idPrestamo = null;
        this.fechaSolicitud = null;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
        this.lector = null;
    }

    public PrestamoDTO(Integer idPrestamo, Date fechaSolicitud, Date fechaPrestamo, Date fechaDevolucion, LectorDTO lector) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaSolicitud = fechaSolicitud;
        this.lector = lector;
    }

    public PrestamoDTO(PrestamoDTO prestamo) {
        this.idPrestamo = prestamo.idPrestamo;
        this.fechaPrestamo = prestamo.fechaPrestamo;
        this.fechaDevolucion = prestamo.fechaDevolucion;
        this.fechaSolicitud = prestamo.fechaSolicitud;
        this.lector = prestamo.lector;
    }

    // Getters y Setters
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
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

    public LectorDTO getLector() {
        return lector;
    }

    public void setLector(LectorDTO lector) {
        this.lector = lector;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
