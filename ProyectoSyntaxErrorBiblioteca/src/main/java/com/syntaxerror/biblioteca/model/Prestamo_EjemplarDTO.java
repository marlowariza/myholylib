package com.syntaxerror.biblioteca.model;

import java.sql.Date;

import com.syntaxerror.biblioteca.model.enums.EstadoPrestamo;

public class Prestamo_EjemplarDTO {
    private PrestamoDTO prestamo;
    private EjemplarDTO ejemplar;
    private EstadoPrestamo estado;
    private Date fechaRealDevolucion;
    
    public Prestamo_EjemplarDTO() {
        this.prestamo = null;
        this.ejemplar = null;
        this.estado = null;
        this.fechaRealDevolucion = null;
    }

    public Prestamo_EjemplarDTO(PrestamoDTO prestamo, EjemplarDTO ejemplar, EstadoPrestamo estado,
            Date fechaRealDevolucion) {
        this.prestamo = prestamo;
        this.ejemplar = ejemplar;
        this.estado = estado;
        this.fechaRealDevolucion = fechaRealDevolucion;
    }
    public Prestamo_EjemplarDTO(Prestamo_EjemplarDTO prestamoEjemplar) {
        this.prestamo = prestamoEjemplar.prestamo;
        this.ejemplar = prestamoEjemplar.ejemplar;
        this.estado = prestamoEjemplar.estado;
        this.fechaRealDevolucion = prestamoEjemplar.fechaRealDevolucion;
    }
    public PrestamoDTO getPrestamo() {
        return prestamo;
    }
    public void setPrestamo(PrestamoDTO prestamo) {
        this.prestamo = prestamo;
    }
    public EjemplarDTO getEjemplar() {
        return ejemplar;
    }
    public void setEjemplar(EjemplarDTO ejemplar) {
        this.ejemplar = ejemplar;
    }
    public EstadoPrestamo getEstado() {
        return estado;
    }
    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
    public Date getFechaRealDevolucion() {
        return fechaRealDevolucion;
    }
    public void setFechaRealDevolucion(Date fechaRealDevolucion) {
        this.fechaRealDevolucion = fechaRealDevolucion;
    }
}