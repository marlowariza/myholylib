package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.PublicoObjetivo;
import java.util.Date;

public class VocabularioDTO extends MaterialDTO {

    private String nivelDificultad;
    private Boolean conEjercicios;

    public VocabularioDTO() {
        this.nivelDificultad = null;
        this.conEjercicios = null;
    }

    public VocabularioDTO(Integer idMaterial, String titulo, String estado, String edicion, Date fechaIngreso,
            Date fechaPublicacion, Integer cantidadEjemplares, PublicoObjetivo lectorObjetivo, String idioma,
            String nivelDificultad, Boolean conEjercicios) {
        super(idMaterial, titulo, estado,edicion,fechaIngreso, fechaPublicacion, cantidadEjemplares,lectorObjetivo, idioma);
        this.nivelDificultad = nivelDificultad;
        this.conEjercicios = conEjercicios;
    }

    public VocabularioDTO(VocabularioDTO vocabulario) {
        super(vocabulario);
        this.nivelDificultad = vocabulario.nivelDificultad;
        this.conEjercicios = vocabulario.conEjercicios;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public Boolean getConEjercicios() {
        return conEjercicios;
    }

    public void setConEjercicios(Boolean conEjercicios) {
        this.conEjercicios = conEjercicios;
    }

}


