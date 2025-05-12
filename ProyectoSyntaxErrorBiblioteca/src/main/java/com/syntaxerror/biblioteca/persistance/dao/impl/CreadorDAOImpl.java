package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.syntaxerror.biblioteca.model.CreadorDTO;
import com.syntaxerror.biblioteca.model.enums.TipoAutor;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.util.List;
import com.syntaxerror.biblioteca.persistance.dao.CreadorDAO;

public class CreadorDAOImpl extends DAOImplBase implements CreadorDAO {

    private CreadorDTO autor;

    public CreadorDAOImpl() {
        super("BIB_CREADOR");
        this.retornarLlavePrimaria = true;
        this.autor = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_AUTOR", true, true));
        this.listaColumnas.add(new Columna("NOMBRE", false, false));
        this.listaColumnas.add(new Columna("PATERNO", false, false));
        this.listaColumnas.add(new Columna("MATERNO", false, false));
        this.listaColumnas.add(new Columna("SEUDONIMO", false, false));
        this.listaColumnas.add(new Columna("TIPO_AUTOR", false, false));
        this.listaColumnas.add(new Columna("NACIONALIDAD", false, false));
        this.listaColumnas.add(new Columna("ACTIVO", false, false));
    }

    @Override
    public Integer insertar(CreadorDTO autor) {
        this.autor = autor;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setString(1, this.autor.getNombre());
            this.statement.setString(2, this.autor.getPaterno());
            this.statement.setString(3, this.autor.getMaterno());
            this.statement.setString(4, this.autor.getSeudonimo());
            this.statement.setString(5, this.autor.getTipo().name());
            this.statement.setString(6, this.autor.getNacionalidad());
            this.statement.setInt(7, this.autor.getActivo() ? 1 : 0);
        } catch (SQLException ex) {
            Logger.getLogger(CreadorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setString(1, this.autor.getNombre());
        this.statement.setString(2, this.autor.getPaterno());
        this.statement.setString(3, this.autor.getMaterno());
        this.statement.setString(4, this.autor.getSeudonimo());
        this.statement.setString(5, this.autor.getTipo().name());
        this.statement.setString(6, this.autor.getNacionalidad());
        this.statement.setInt(7, this.autor.getActivo() ? 1 : 0);
        this.statement.setInt(8, this.autor.getIdAutor());
        //En modificar el ID va al ultimo
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.autor.getIdAutor());
        //Para eliminar solo va el id
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.autor.getIdAutor());
        //Para obtener por Id igual solo el id
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.autor = new CreadorDTO();
        autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
        autor.setNombre(this.resultSet.getString("NOMBRE"));
        autor.setPaterno(this.resultSet.getString("PATERNO"));
        autor.setMaterno(this.resultSet.getString("MATERNO"));
        autor.setSeudonimo(this.resultSet.getString("SEUDONIMO"));
        autor.setTipo(TipoAutor.valueOf(this.resultSet.getString("TIPO_AUTOR")));
        autor.setNacionalidad(this.resultSet.getString("NACIONALIDAD"));
        autor.setActivo(this.resultSet.getInt("ACTIVO") == 1);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.autor = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.autor);
    }

    @Override
    public CreadorDTO obtenerPorId(Integer idAutor) {
        this.autor = new CreadorDTO();
        this.autor.setIdAutor(idAutor);
        super.obtenerPorId();
        return this.autor;
    }

    @Override
    public ArrayList<CreadorDTO> listarTodos() {
        return (ArrayList<CreadorDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CreadorDTO autor) {
        this.autor = autor;
        return super.modificar();
    }

    @Override
    public Integer eliminar(CreadorDTO autor) {
        this.autor = autor;
        return super.eliminar();
    }

}
