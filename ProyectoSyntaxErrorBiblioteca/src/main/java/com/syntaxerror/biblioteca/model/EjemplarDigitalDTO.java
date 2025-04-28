package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.FormatoDigital;

public class EjemplarDigitalDTO {
    private Integer idEjemplarD;
    private FormatoDigital formato;
    private MaterialDTO material;

    public EjemplarDigitalDTO() {
        this.idEjemplarD = null;
        this.formato = null;
        this.material = null;
    }
    public EjemplarDigitalDTO(Integer idEjemplarD, FormatoDigital formato, MaterialDTO material) {
        this.idEjemplarD = idEjemplarD;
        this.formato = formato;
        this.material = material;
    }
    public EjemplarDigitalDTO(EjemplarDigitalDTO ejemplarDigital) {
        this.idEjemplarD = ejemplarDigital.idEjemplarD;
        this.formato = ejemplarDigital.formato;
        this.material = ejemplarDigital.material;
    }
    public Integer getIdEjemplarD() {
        return idEjemplarD;
    }
    public void setIdEjemplarD(Integer idEjemplarD) {
        this.idEjemplarD = idEjemplarD;
    }
    public FormatoDigital getFormato() {
        return formato;
    }
    public void setFormato(FormatoDigital formato) {
        this.formato = formato;
    }
    public MaterialDTO getMaterial() {
        return material;
    }
    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }
}
