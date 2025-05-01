package com.syntaxerror.biblioteca.model;
import com.syntaxerror.biblioteca.model.enums.Categoria;

public class TemaDTO {

    private Integer idTema;
    private String descripcion;
    private Categoria categoria;

    // Constructores
    public TemaDTO() {
        this.idTema = null;
        this.descripcion = null;
        this.categoria = null;
    }

    public TemaDTO(Integer idTema, String descripcion, Categoria categoria) {
        this.idTema = idTema;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public TemaDTO(TemaDTO tema) {
        this.idTema = tema.idTema;
        this.descripcion = tema.descripcion;
        this.categoria = tema.categoria;
    }
    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}