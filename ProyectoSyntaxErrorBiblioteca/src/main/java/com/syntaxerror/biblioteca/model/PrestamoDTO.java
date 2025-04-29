package com.syntaxerror.biblioteca.model;

import java.util.ArrayList;
import java.util.Date;

public class PrestamoDTO {

    private String idPrestamo;
    private Date fechaSolicitud;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private LectorDTO lector;
    private ArrayList<Prestamo_EjemplarDTO> ejemplares;

    // Constructores
    public PrestamoDTO() {
        this.idPrestamo = null;
        this.fechaSolicitud = null;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
        this.lector = null;
        this.ejemplares = new ArrayList<>();
    }

    public PrestamoDTO(String idPrestamo, Date fechaSolicitud, Date fechaPrestamo, Date fechaDevolucion, LectorDTO lector) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaSolicitud = fechaSolicitud;
        this.lector = lector;
        this.ejemplares = new ArrayList<>();
    }

    public PrestamoDTO(PrestamoDTO prestamo) {
        this.idPrestamo = prestamo.idPrestamo;
        this.fechaPrestamo = prestamo.fechaPrestamo;
        this.fechaDevolucion = prestamo.fechaDevolucion;
        this.fechaSolicitud = prestamo.fechaSolicitud;
        this.lector = prestamo.lector;
        this.ejemplares = new ArrayList<>(prestamo.ejemplares);

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
    public void agregarEjemplar(Prestamo_EjemplarDTO m) {
        if (!this.ejemplares.contains(m)) {
            this.ejemplares.add(m);
        }
    }
}
