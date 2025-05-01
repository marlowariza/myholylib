package com.syntaxerror.biblioteca.model;

import com.syntaxerror.biblioteca.model.enums.Turnos;
import java.util.Date;

public class BibliotecarioDTO extends PersonaDTO {
    
    private Integer idBibliotecario;
    private Turnos turno;
    private Date fechaContratoInicio;
    private Date fechaContratoFinal;
    private Boolean vigente;

    //Constructores
    public BibliotecarioDTO() {
        this.idBibliotecario=null;
        this.fechaContratoInicio = null;
        this.fechaContratoFinal = null;
        this.vigente=null;
    }

    public BibliotecarioDTO(Integer idPersona, String nombre, String direccion,
            String telefono, String correo, String contrasenha, SedeDTO sede,
            Turnos turno,Integer idBibliotecario ,Date fechaContratoInicio,Date fechaContratoFinal,Boolean vigente) {
        super(idPersona, nombre,direccion,
                telefono, correo, contrasenha,sede);
        this.idBibliotecario=idBibliotecario;
        this.turno = turno;
        this.fechaContratoInicio = fechaContratoInicio;
        this.fechaContratoFinal = fechaContratoFinal;
        this.vigente=vigente;
    }

    public BibliotecarioDTO(BibliotecarioDTO bibliotecario) {
        super(bibliotecario);
        this.idBibliotecario=bibliotecario.idBibliotecario;
        this.turno = bibliotecario.turno;
        this.fechaContratoInicio = bibliotecario.fechaContratoInicio;
        this.fechaContratoFinal = bibliotecario.fechaContratoFinal;
    }

    //Getters y Setters
    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Integer idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaContratoInicio() {
        return fechaContratoInicio;
    }

    public void setFechaContratoInicio(Date fechaContratoInicio) {
        this.fechaContratoInicio = fechaContratoInicio;
    }

    public Date getFechaContratoFinal() {
        return fechaContratoFinal;
    }

    public void setFechaContratoFinal(Date fechaContratoFinal) {
        this.fechaContratoFinal = fechaContratoFinal;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

}
