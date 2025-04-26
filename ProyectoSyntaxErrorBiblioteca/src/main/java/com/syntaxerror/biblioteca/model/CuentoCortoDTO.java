package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.GeneroLiterario;
import com.syntaxerror.biblioteca.model.enums.PublicoObjetivo;
import java.util.Date;

public class CuentoCortoDTO extends MaterialDTO {

    private Integer numeroCuentos;
    private GeneroLiterario genero;

    public CuentoCortoDTO() {
        super();
        this.numeroCuentos = null;
        this.genero = null;
    }

    public CuentoCortoDTO(Integer idMaterial, String titulo, String estado, String edicion, Integer cantidadEjemplares,
                        PublicoObjetivo lectorObjetivo,Date fechaIngreso,Date fechaPublicacion ,String idioma) {
        super(idMaterial, titulo, estado,edicion,fechaIngreso, fechaPublicacion, cantidadEjemplares,lectorObjetivo, idioma);
        this.numeroCuentos = numeroCuentos;
        this.genero = genero;
    }

    public CuentoCortoDTO(CuentoCortoDTO otroCuentoCorto) {
        super(otroCuentoCorto);
        this.numeroCuentos = otroCuentoCorto.numeroCuentos;
        this.genero = otroCuentoCorto.genero;
    }

    public int getNumeroCuentos() {
        return numeroCuentos;
    }

    public void setNumeroCuentos(int numeroCuentos) {
        this.numeroCuentos = numeroCuentos;
    }

    public GeneroLiterario getGenero() {
        return genero;
    }

    public void setGenero(GeneroLiterario genero) {
        this.genero = genero;
    }
}
