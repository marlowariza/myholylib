package com.syntaxerror.biblioteca.model;

public class MaterialTemaDTO {
    private Integer idMaterial;
    private Integer idTema;

    public MaterialTemaDTO() {
        this.idMaterial = null;
        this.idTema = null;
    }

    public MaterialTemaDTO(Integer idMaterial, Integer idTema) {
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
