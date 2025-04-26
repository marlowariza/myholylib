
package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.PublicoObjetivo;
import com.syntaxerror.biblioteca.model.enums.TipoIdioma;
import java.util.Date;


public class DiccionarioDTO extends MaterialDTO{

    private Integer numeroEntradas;
    private Boolean esIlustrado;
    private TipoIdioma idioma;

    // Constructores
    public DiccionarioDTO() {
        numeroEntradas=null;
        esIlustrado=null;
    }

    public DiccionarioDTO(Integer idLibro, String titulo, String estado, String edicion, Integer cantidadEjemplares,
                        PublicoObjetivo lectorObjetivo,Date fechaIngreso,Date fechaPublicacion ,String idioma, Integer numeroEntradas, Boolean esIlustrado, TipoIdioma tid) {
                       
        super(idLibro, titulo, estado, edicion,fechaIngreso,fechaPublicacion, cantidadEjemplares, lectorObjetivo, idioma);
        this.numeroEntradas = numeroEntradas;
        this.esIlustrado = esIlustrado;
        this.idioma=tid;
    }
    
    public DiccionarioDTO(DiccionarioDTO diccionario) {
    super(diccionario); 
    this.numeroEntradas =diccionario.numeroEntradas ;
    this.esIlustrado = diccionario.esIlustrado;
    this.idioma=diccionario.idioma;
}

    // Getters y Setters

    public Integer getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(Integer numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public Boolean getEsIlustrado() {
        return esIlustrado;
    }

    public void setEsIlustrado(Boolean esIlustrado) {
        this.esIlustrado = esIlustrado;
    }

    public TipoIdioma getTipoIdioma() {
        return idioma;
    }

    public void setTipoIdioma(TipoIdioma idioma) {
        this.idioma = idioma;
    }    

}
