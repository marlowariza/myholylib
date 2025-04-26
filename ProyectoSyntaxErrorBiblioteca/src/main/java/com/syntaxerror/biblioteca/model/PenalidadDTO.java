
package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.TipoPenalidad;
import java.util.Date;

public class PenalidadDTO {
    private Integer idPenalidad;
    private Date fecha;
    private Double monto;
    private String descripcion;
    private TipoPenalidad tipo;
    private LectorDTO lector;
    // Constructores
    public PenalidadDTO() {
        this.idPenalidad=null;
        this.fecha=null;
        this.monto=null;
        this.descripcion=null;
    }

    public PenalidadDTO(Integer idPenalidad, Date fecha, Double monto, String descripcion,
                        TipoPenalidad tipo,LectorDTO lector) {
        this.idPenalidad = idPenalidad;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.lector = lector;
    }

    public PenalidadDTO(PenalidadDTO penalidad) {
        this.idPenalidad = penalidad.idPenalidad;
        this.fecha = penalidad.fecha;
        this.monto = penalidad.monto;
        this.descripcion = penalidad.descripcion;
        this.tipo = penalidad.tipo;
        this.lector=penalidad.lector;
    }

    // Getters y Setters

    public LectorDTO getLector() {
        return lector;
    }

    public void setLector(LectorDTO lector) {
        this.lector = lector;
    }
    
    public Integer getIdPenalidad() {
        return idPenalidad;
    }

    public void setIdPenalidad(Integer idPenalidad) {
        this.idPenalidad = idPenalidad;
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

    public TipoPenalidad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPenalidad tipo) {
        this.tipo = tipo;
    }
}
