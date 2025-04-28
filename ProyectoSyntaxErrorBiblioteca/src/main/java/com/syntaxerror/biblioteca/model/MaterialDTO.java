package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import java.util.ArrayList;

public class MaterialDTO {

    private Integer idMaterial;
    private String titulo;
    private String edicion;
    private NivelDeIngles nivel;
    private Integer anioPublicacion;
    private EditorialDTO editorial;
    private ArrayList<EjemplarDTO> ejemplares;
    private ArrayList<TemaDTO> temas;
    private ArrayList<EjemplarDigitalDTO> ejemplaresDigitales;

    // Constructores
    public MaterialDTO() {
        this.idMaterial = null;
        this.titulo = null;
        this.edicion = null;
        this.nivel= null;
        this.anioPublicacion= null;
        this.editorial = null;
        this.temas = new ArrayList<>();
        this.ejemplares = new ArrayList<>();
        this.ejemplaresDigitales = new ArrayList<>();
    }
    public MaterialDTO(Integer idMaterial, String titulo, String edicion, NivelDeIngles nivel, Integer anioPublicacion,
            EditorialDTO editorial) {
        this.idMaterial = idMaterial;
        this.titulo = titulo;
        this.edicion = edicion;
        this.nivel = nivel;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.temas = new ArrayList<>();
        this.ejemplares = new ArrayList<>();
        this.ejemplaresDigitales = new ArrayList<>();
    }
    public MaterialDTO(MaterialDTO material) {
        this.idMaterial = material.idMaterial;
        this.titulo = material.titulo;
        this.edicion = material.edicion;
        this.nivel = material.nivel;
        this.anioPublicacion = material.anioPublicacion;
        this.editorial= material.editorial;
        this.ejemplares = new ArrayList<>(material.ejemplares);
        this.temas = new ArrayList<>(material.temas);
        this.ejemplaresDigitales = new ArrayList<>(material.ejemplaresDigitales);
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }


    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getEdicion() {
        return edicion;
    }


    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }


    public NivelDeIngles getNivel() {
        return nivel;
    }


    public void setNivel(NivelDeIngles nivel) {
        this.nivel = nivel;
    }


    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }


    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }


    public EditorialDTO getEditorial() {
        return editorial;
    }


    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

    public void agregarEjemplar(EjemplarDTO m) {
        if (!this.ejemplares.contains(m)) {
            this.ejemplares.add(m);
        }
    }
    public void quitarEjemplar(EjemplarDTO m) {
        if (ejemplares.contains(m)) {
            ejemplares.remove(m);
        }
    }
    public void agregarEjemplarDigital(EjemplarDigitalDTO m) {
        if (!this.ejemplaresDigitales.contains(m)) {
            this.ejemplaresDigitales.add(m);
        }
    }
    public void agregarTema(TemaDTO m) {
        if (!this.temas.contains(m)) {
            this.temas.add(m);
            m.agregarMaterial(this); // relación bidireccional
        }
    }

}
