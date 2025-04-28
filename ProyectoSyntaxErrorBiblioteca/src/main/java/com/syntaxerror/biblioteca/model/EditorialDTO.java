package com.syntaxerror.biblioteca.model;
import java.util.ArrayList;

public class EditorialDTO{
    private Integer idEditorial;
    private String nombre;
    private String sitioweb;
    private String pais;
    private ArrayList<MaterialDTO> materiales;

    public EditorialDTO() {
        this.idEditorial = null;
        this.nombre = null;
        this.sitioweb = null;
        this.pais = null;
        this.materiales = new ArrayList<>();
    }
    public EditorialDTO(Integer idEditorial, String nombre, String sitioweb, String pais) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.sitioweb = sitioweb;
        this.pais = pais;
        this.materiales = new ArrayList<>();
    }
    public EditorialDTO(EditorialDTO editorial) {
        this.idEditorial = editorial.idEditorial;
        this.nombre = editorial.nombre;
        this.sitioweb = editorial.sitioweb;
        this.pais = editorial.pais;
        this.materiales = new ArrayList<>(editorial.materiales);
    }
    public Integer getIdEditorial() {
        return idEditorial;
    }
    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSitioweb() {
        return sitioweb;
    }
    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void agregarMaterial(MaterialDTO m) {
        if (!this.materiales.contains(m)) {
            this.materiales.add(m);
        }
    }
}
