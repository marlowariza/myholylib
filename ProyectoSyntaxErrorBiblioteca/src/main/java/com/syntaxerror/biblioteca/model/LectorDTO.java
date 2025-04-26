package com.syntaxerror.biblioteca.model;

import java.util.ArrayList;
import java.util.Date;

public class LectorDTO extends PersonaDTO {

    private Integer categoria;
    private Integer cantidadPrestamos;
    private Integer cantidadPenalizaciones;
    private ArrayList<PenalidadDTO> penalidades;
    private ArrayList<PrestamoDTO> prestamos;

    // Constructores
    public LectorDTO() {
        this.categoria = null;
        this.cantidadPenalizaciones = null;
        this.cantidadPrestamos = null;
        this.penalidades = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public LectorDTO(Integer idPersona, Date fechaNacimiento, String nombre, String direccion,
            String telefono, String email, String contrasenha, Integer categoria,
            Integer cantidadPrestamos, Integer cantidadPenalizaciones) {
        super(idPersona, fechaNacimiento, nombre, direccion, telefono, email, contrasenha);
        this.categoria = categoria;
        this.cantidadPrestamos = cantidadPrestamos;
        this.cantidadPenalizaciones = cantidadPenalizaciones;
        this.penalidades = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public LectorDTO(LectorDTO lector) {
        super(lector);
        this.categoria = lector.categoria;
        this.cantidadPrestamos = lector.cantidadPrestamos;
        this.cantidadPenalizaciones = lector.cantidadPenalizaciones;
        this.penalidades = new ArrayList<>(lector.penalidades);
        this.prestamos = new ArrayList<>(lector.prestamos);
    }

    //Getters y setters
    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public void setCantidadPrestamos(Integer cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }

    public Integer getCantidadPenalizaciones() {
        return cantidadPenalizaciones;
    }

    public void setCantidadPenalizaciones(Integer cantidadPenalizaciones) {
        this.cantidadPenalizaciones = cantidadPenalizaciones;
    }

    public void agregarPenalidad(PenalidadDTO penalidad) {
        if (penalidad != null) {
            this.penalidades.add(penalidad);
            this.cantidadPenalizaciones = this.penalidades.size();
        }
    }

    public void agregarPrestamo(PrestamoDTO prestamo) {
        if (prestamo != null) {
            this.prestamos.add(prestamo);
            this.cantidadPrestamos = this.prestamos.size();
        }
    }

}
