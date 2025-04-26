package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.GeneroLiterario;
import com.syntaxerror.biblioteca.model.enums.PublicoObjetivo;
import java.util.Date;

public class LiteraturaDTO extends MaterialDTO {

    private GeneroLiterario genero;

    public LiteraturaDTO() {
        this.genero = null;
    }

    public LiteraturaDTO(Integer idMaterial, String titulo, String estado, String edicion, Date fechaIngreso,
                    Date fechaPublicacion,Integer cantidadEjemplares, PublicoObjetivo lectorObjetivo, String idioma, GeneroLiterario genero) {
        super(idMaterial, titulo, estado,edicion,fechaIngreso, fechaPublicacion, cantidadEjemplares,lectorObjetivo, idioma);
        this.genero = genero;
    }

    public LiteraturaDTO(LiteraturaDTO literatura) {
        super(literatura);
        this.genero = literatura.genero;
    }

    public GeneroLiterario getGenero() {
        return genero;
    }

    public void setGenero(GeneroLiterario genero) {
        this.genero = genero;
    }
}

