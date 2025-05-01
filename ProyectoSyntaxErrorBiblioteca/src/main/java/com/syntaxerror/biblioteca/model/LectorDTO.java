package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;

public class LectorDTO extends PersonaDTO {

    private Integer idLector;
    private NivelDeIngles nivel;

    // Constructores
    public LectorDTO() {
        this.idLector = null;
        this.nivel = null;
    }

    public LectorDTO(Integer idPersona, String nombre, String direccion,
            String telefono, String correo, String contrasenha,SedeDTO sede, Integer idLector, NivelDeIngles nivel) {
        super(idPersona, nombre, direccion, telefono, correo, contrasenha,sede);
        this.idLector = idLector;
        this.nivel = nivel;
    }

    public LectorDTO(LectorDTO lector) {
        super(lector);
        this.idLector = lector.idLector;
        this.nivel = lector.nivel;
    }

    //Getters y setters
    public Integer getIdLector() {
        return idLector;
    }

    public void setIdLector(Integer idLector) {
        this.idLector = idLector;
    }

    public NivelDeIngles getNivel() {
        return nivel;
    }

    public void setNivel(NivelDeIngles nivel) {
        this.nivel = nivel;
    }
}
