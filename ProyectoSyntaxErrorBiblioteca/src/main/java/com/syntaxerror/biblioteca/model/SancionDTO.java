package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.TipoSancion;
import java.util.Date;

public class SancionDTO {

    private Integer idsancion;
    private TipoSancion tipo;
    private Date fecha;
    private Double monto;
    private Date duracion;
    private String descripcion;
    private PrestamoDTO prestamo;
    // Constructores
    public SancionDTO() {
        this.idsancion = null;
        this.tipo = null;
        this.fecha = null;
        this.monto = null;
        this.duracion = null;
        this.descripcion = null;
        this.prestamo = null;
    }

    public SancionDTO(Integer idSancion, TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion,
            PrestamoDTO prestamo) {
        this.idsancion = idSancion;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.duracion = duracion;
        this.prestamo = prestamo;
    }

    public SancionDTO(SancionDTO penalidad) {
        this.idsancion = penalidad.idsancion;
        this.fecha = penalidad.fecha;
        this.monto = penalidad.monto;
        this.descripcion = penalidad.descripcion;
        this.tipo = penalidad.tipo;
        this.duracion = penalidad.duracion;
        this.prestamo = penalidad.prestamo;
    }

    // Getters y Setters
    public Integer getIdsancion() {
        return idsancion;
    }

    public void setIdsancion(Integer idsancion) {
        this.idsancion = idsancion;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public PrestamoDTO getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(PrestamoDTO prestamo) {
        this.prestamo = prestamo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoSancion getTipo() {
        return tipo;
    }

    public void setTipo(TipoSancion tipo) {
        this.tipo = tipo;
    }
}
