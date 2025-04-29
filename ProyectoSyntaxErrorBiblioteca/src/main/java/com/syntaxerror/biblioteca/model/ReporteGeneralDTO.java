package com.syntaxerror.biblioteca.model;

public class ReporteGeneralDTO {
    private Integer anio;
    private Integer mes;
    private PrestamoDTO prestamo;
    private LectorDTO lector;

    // Constructores
    public ReporteGeneralDTO() {
        this.anio = null;
        this.mes = null;
        this.prestamo = null;
        this.lector = null;
    }

    public ReporteGeneralDTO(Integer anio, Integer mes, PrestamoDTO prestamo, LectorDTO lector) {
        this.anio = anio;
        this.mes = mes;
        this.prestamo = prestamo;
        this.lector = lector;
    }

    // Getters y Setters
    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public PrestamoDTO getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(PrestamoDTO prestamo) {
        this.prestamo = prestamo;
    }

    public LectorDTO getLector() {
        return lector;
    }

    public void setLector(LectorDTO lector) {
        this.lector = lector;
    }
}