package com.syntaxerror.biblioteca.model;

public class MaterialAutorDTO {
    private Integer idAutor;
    private Integer idMaterial;

    public MaterialAutorDTO() {
        this.idMaterial = null;
        this.idAutor = null;
    }

    public MaterialAutorDTO(Integer idMaterial, Integer idAutor) {
        this.idMaterial = idMaterial;
        this.idAutor = idAutor;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }
    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }
    public Integer getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }
}
