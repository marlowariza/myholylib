package com.syntaxerror.biblioteca.model;
import com.syntaxerror.biblioteca.model.enums.Categoria;
import java.util.ArrayList;

public class TemaDTO {

    private Integer idTema;
    private String descripcion;
    private Categoria categoria;
    private ArrayList<MaterialDTO> materiales;

    // Constructores
    public TemaDTO() {
        this.idTema = null;
        this.descripcion = null;
        this.categoria = null;
        this.materiales = new ArrayList<>();
    }

    public TemaDTO(Integer idTema, String descripcion, Categoria categoria) {
        this.idTema = idTema;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.materiales = new ArrayList<>();
    }

    public TemaDTO(TemaDTO tema) {
        this.idTema = tema.idTema;
        this.descripcion = tema.descripcion;
        this.categoria = tema.categoria;
        this.materiales = new ArrayList<>(tema.materiales);
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
    
    public void agregarMaterial(MaterialDTO m) {
        if (!this.materiales.contains(m)) {
            this.materiales.add(m);
        }
    }
}