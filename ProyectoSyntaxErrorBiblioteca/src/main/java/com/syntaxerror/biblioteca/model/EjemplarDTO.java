package com.syntaxerror.biblioteca.model;

import java.util.Date;

/**
 *
 * @author josue
 */
public class EjemplarDTO {

    private Integer idEjemplar;
    private Date fechaAdquisicion;
    private Boolean disponible;
    private String ubicacion;

    private SedeDTO sede;
    private MaterialDTO material;

    
    public EjemplarDTO() {
        this.idEjemplar = null;
        this.fechaAdquisicion = null;
        this.disponible = null;
        this.ubicacion = null;
        this.sede = null;
        this.material = null;
    }

    public EjemplarDTO(Integer idEjemplar, Date fechaAdquisicion, Boolean disponible,
            String ubicacion, SedeDTO sede, MaterialDTO material) {
        this.idEjemplar = idEjemplar;
        this.fechaAdquisicion = fechaAdquisicion;
        this.disponible = disponible;
        this.ubicacion = ubicacion;
        this.sede = sede;
        this.material = material;
    }

    /**
     * @return the idEjem
     */
    public Integer getIdEjemplar() {
        return idEjemplar;
    }

    /**
     * @param idEjemplar the idEjem to set
     */
    public void setIdEjemplar(Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    /**
     * @return the fechaAdquisicion
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * @param fechaAdquisicion the fechaAdquisicion to set
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * @return the disponible
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the idSede
     *
     *
     * /
     **
     * @return the idMaterial
     */
    public SedeDTO getSede() {
        return sede;
    }

    public void setSede(SedeDTO sede) {
        this.sede = sede;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }
    
}
