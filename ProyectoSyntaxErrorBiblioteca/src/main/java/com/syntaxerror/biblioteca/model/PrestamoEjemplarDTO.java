package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.EstadoPrestamo;
import java.util.Date;

public class PrestamoEjemplarDTO {
    private Integer idPrestamo;
    private Integer idEjemplar;
    private EstadoPrestamo estado; // Puedes cambiar esto a un Enum si lo deseas
    private Date fechaRealDevolucion;

    // Constructores
    public PrestamoEjemplarDTO() {
        this.idPrestamo = null;
        this.idEjemplar = null;
        this.estado = null;
        this.fechaRealDevolucion = null;
    }

    public PrestamoEjemplarDTO(Integer idPrestamo, Integer idEjemplar, EstadoPrestamo estado, Date fechaRealDevolucion) {
        this.idPrestamo = idPrestamo;
        this.idEjemplar = idEjemplar;
        this.estado = estado;
        this.fechaRealDevolucion = fechaRealDevolucion;
    }

    // Getters y Setters
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
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