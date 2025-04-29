package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.EstadoPrestamo;
import java.util.Date;

public class PrestamoEjemplarDTO {
    private PrestamoDTO prestamo;
    private EjemplarDTO ejemplar;
    private EstadoPrestamo estado; // Puedes cambiar esto a un Enum si lo deseas
    private Date fechaRealDevolucion;

    // Constructores
    public PrestamoEjemplarDTO() {
        this.prestamo = null;
        this.ejemplar = null;
        this.estado = null;
        this.fechaRealDevolucion = null;
    }

    public PrestamoEjemplarDTO(PrestamoDTO prestamo, EjemplarDTO ejemplar, EstadoPrestamo estado, Date fechaRealDevolucion) {
        this.prestamo = prestamo;
        this.ejemplar = ejemplar;
        this.estado = estado;
        this.fechaRealDevolucion = fechaRealDevolucion;
    }

    // Getters y Setters
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