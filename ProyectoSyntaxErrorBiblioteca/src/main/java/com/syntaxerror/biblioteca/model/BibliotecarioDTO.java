package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.Turnos;
import java.util.Date;

public class BibliotecarioDTO extends PersonaDTO {

    private Turnos turno;
    private Date fechaContrato;

    //Constructores
    public BibliotecarioDTO() {
        this.fechaContrato = null;
    }

    public BibliotecarioDTO(Integer idPersona, Date fechaNacimiento, String nombre, String direccion,
            String telefono, String email, String contrasenha,
            Turnos turno, Date fechaContrato) {
        super(idPersona, fechaNacimiento, nombre, direccion,
                telefono, email, contrasenha);
        this.turno = turno;
        this.fechaContrato = fechaContrato;
    }

    public BibliotecarioDTO(BibliotecarioDTO bibliotecario) {
        super(bibliotecario);
        this.turno = bibliotecario.turno;
        this.fechaContrato = bibliotecario.fechaContrato;
    }

    //Getters y Setters
    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }
}
