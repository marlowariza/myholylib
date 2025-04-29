package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.EstadoPrestamo;
import java.util.ArrayList;
import java.util.Date;

public class PrestamoDTO {

    private String idPrestamo;
    private Date fechaSolicitud;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private LectorDTO lector;
    private ArrayList<PrestamoEjemplarDTO> prestamosEjemplares;
    private ArrayList<SancionDTO> sanciones;

    // Constructores
    public PrestamoDTO() {
        this.idPrestamo = null;
        this.fechaSolicitud = null;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
        this.lector = null;
        this.prestamosEjemplares = new ArrayList<>();
        this.sanciones = new ArrayList<>();
    }

    public PrestamoDTO(String idPrestamo, Date fechaSolicitud, Date fechaPrestamo, Date fechaDevolucion, LectorDTO lector) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaSolicitud = fechaSolicitud;
        this.lector = lector;
        this.prestamosEjemplares = new ArrayList<>();
        this.sanciones = new ArrayList<>();
    }

    public PrestamoDTO(PrestamoDTO prestamo) {
        this.idPrestamo = prestamo.idPrestamo;
        this.fechaPrestamo = prestamo.fechaPrestamo;
        this.fechaDevolucion = prestamo.fechaDevolucion;
        this.fechaSolicitud = prestamo.fechaSolicitud;
        this.lector = prestamo.lector;
        this.sanciones = new ArrayList<>(prestamo.sanciones);
        this.prestamosEjemplares = new ArrayList<>(prestamo.prestamosEjemplares);

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


    public void agregarSancion(SancionDTO sancion) {
        if (sancion != null) {
            this.sanciones.add(sancion);
        }
    }
    
    public void agregarEjemplar(EjemplarDTO ejemplar, EstadoPrestamo estado, Date fechaRealDevolucion) {
    PrestamoEjemplarDTO nuevoRegistro = new PrestamoEjemplarDTO(this, ejemplar, estado, fechaRealDevolucion);
    this.prestamosEjemplares.add(nuevoRegistro);
}

}
