package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.PersonaDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.model.enums.TipoPersona;
import com.syntaxerror.biblioteca.model.enums.Turnos;
import com.syntaxerror.biblioteca.persistance.dao.PersonaDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImpl extends DAOImplBase implements PersonaDAO {

    private PersonaDTO persona;

    public PersonaDAOImpl() {
        super("BIB_PERSONA");
        this.retornarLlavePrimaria = true;
        this.persona = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_PERSONA", true, true));
        this.listaColumnas.add(new Columna("NOMBRE", false, false));
        this.listaColumnas.add(new Columna("PATERNO", false, false));
        this.listaColumnas.add(new Columna("MATERNO", false, false));
        this.listaColumnas.add(new Columna("DIRECCION", false, false));
        this.listaColumnas.add(new Columna("TELEFONO", false, false));
        this.listaColumnas.add(new Columna("CORREO", false, false));
        this.listaColumnas.add(new Columna("CONTRASENHA", false, false));
        this.listaColumnas.add(new Columna("TIPO_PERSONA", false, false));
        this.listaColumnas.add(new Columna("NIVEL", false, false));
        this.listaColumnas.add(new Columna("TURNO", false, false));
        this.listaColumnas.add(new Columna("FECHA_CONTRATO_INI", false, false));
        this.listaColumnas.add(new Columna("FECHA_CONTRATO_FIN", false, false));
        this.listaColumnas.add(new Columna("VIGENTE", false, false));
        this.listaColumnas.add(new Columna("SEDE_IDSEDE", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {

        //si es autoincremental, se salta el (1,ID)
        this.statement.setString(1, this.persona.getNombre());
        this.statement.setString(2, this.persona.getPaterno());
        this.statement.setString(3, this.persona.getMaterno());
        this.statement.setString(4, this.persona.getDireccion());
        this.statement.setString(5, this.persona.getTelefono());
        this.statement.setString(6, this.persona.getCorreo());
        this.statement.setString(7, this.persona.getContrasenha());
        this.statement.setString(8, this.persona.getTipo().name());
        this.statement.setString(9, this.persona.getNivel().name());
        this.statement.setString(10, this.persona.getTurno().name());
        this.statement.setDate(11, new java.sql.Date(this.persona.getFechaContratoInicio().getTime()));
        this.statement.setDate(12, new java.sql.Date(this.persona.getFechaContratoFinal().getTime()));
        this.statement.setInt(13, this.persona.getVigente() ? 1 : 0);
        this.statement.setInt(14, this.persona.getSede().getIdSede());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        incluirValorDeParametrosParaInsercion(); // mismos campos que inserción
        this.statement.setInt(15, this.persona.getIdPersona());
        //En modificar el ID va al ultimo
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.persona.getIdPersona());
        //Para eliminar solo va el id
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.persona.getIdPersona());
        //Para obtener por Id igual solo el id
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.persona = new PersonaDTO();
        this.persona.setIdPersona(this.resultSet.getInt("ID_PERSONA"));
        this.persona.setNombre(this.resultSet.getString("NOMBRE"));
        this.persona.setPaterno(this.resultSet.getString("PATERNO"));
        this.persona.setMaterno(this.resultSet.getString("MATERNO"));
        this.persona.setDireccion(this.resultSet.getString("DIRECCION"));
        this.persona.setTelefono(this.resultSet.getString("TELEFONO"));
        this.persona.setCorreo(this.resultSet.getString("CORREO"));
        this.persona.setContrasenha(this.resultSet.getString("CONTRASENHA"));
        this.persona.setTipo(TipoPersona.valueOf(this.resultSet.getString("TIPO_PERSONA")));
        this.persona.setNivel(NivelDeIngles.valueOf(this.resultSet.getString("NIVEL")));
        this.persona.setTurno(Turnos.valueOf(this.resultSet.getString("TURNO")));
        this.persona.setFechaContratoInicio(this.resultSet.getDate("FECHA_CONTRATO_INI"));
        this.persona.setFechaContratoFinal(this.resultSet.getDate("FECHA_CONTRATO_FIN"));
        this.persona.setVigente(this.resultSet.getInt("VIGENTE") == 1);

        // Crear objetos DTO básicos para las relaciones
        SedeDTO sede = new SedeDTO();
        sede.setIdSede(this.resultSet.getInt("SEDE_IDSEDE"));
        this.persona.setSede(sede);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.persona = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.persona);
    }

    @Override
    public Integer insertar(PersonaDTO persona) {
        this.persona = persona;
        return super.insertar();
    }

    @Override
    public PersonaDTO obtenerPorId(Integer idpersona) {
        this.persona = new PersonaDTO();
        this.persona.setIdPersona(idpersona);
        super.obtenerPorId();
        return this.persona;
    }

    @Override
    public ArrayList<PersonaDTO> listarTodos() {
        return (ArrayList<PersonaDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(PersonaDTO persona) {
        this.persona = persona;
        return super.modificar();
    }

    @Override
    public Integer eliminar(PersonaDTO persona) {
        this.persona = persona;
        return super.eliminar();
    }
}
