package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import java.util.ArrayList;

public class LectorDTO extends PersonaDTO {

    private Integer idLector;
    private NivelDeIngles nivel;
    private ArrayList<PrestamoDTO> prestamos;

    // Constructores
    public LectorDTO() {
        this.idLector = null;
        this.nivel = null;
        this.prestamos = new ArrayList<>();
    }

    public LectorDTO(Integer idPersona, String nombre, String direccion,
            String telefono, String correo, String contrasenha, Integer idLector, NivelDeIngles nivel) {
        super(idPersona, nombre, direccion, telefono, correo, contrasenha);
        this.idLector = idLector;
        this.nivel = nivel;
        this.prestamos = new ArrayList<>();
    }

    public LectorDTO(LectorDTO lector) {
        super(lector);
        this.idLector = lector.idLector;
        this.nivel = lector.nivel;
        this.prestamos = new ArrayList<>(lector.prestamos);
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


    public void agregarPrestamo(PrestamoDTO prestamo) {
        if (prestamo != null) {
            this.prestamos.add(prestamo);
        }
    }

}
