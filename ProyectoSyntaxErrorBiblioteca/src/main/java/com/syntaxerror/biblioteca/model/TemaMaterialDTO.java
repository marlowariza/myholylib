package com.syntaxerror.biblioteca.model;

public class TemaMaterialDTO {
    private Integer idMaterial;
    private Integer idTema;

    public TemaMaterialDTO() {
        this.idMaterial = null;
        this.idTema = null;
    }

    public TemaMaterialDTO(Integer idMaterial, Integer idTema) {
        this.idMaterial = idMaterial;
        this.idTema = idTema;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }
    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }
    public Integer getIdTema() {
        return idTema;
    }
    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }
}
