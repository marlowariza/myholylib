package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.syntaxerror.biblioteca.model.AutorDTO;
import com.syntaxerror.biblioteca.persistance.dao.AutorDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.util.List;

public class AutorDAOImpl extends DAOImplBase implements AutorDAO {

    private AutorDTO autor;

    public AutorDAOImpl() {
        super("BIB_AUTOR");
        this.retornarLlavePrimaria = true;
        this.autor = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_AUTOR", true, true));
        this.listaColumnas.add(new Columna("NOMBRE_COMPLETO", false, false));
        this.listaColumnas.add(new Columna("NACIONALIDAD", false, false));
        this.listaColumnas.add(new Columna("ACTIVO", false, false));
        this.listaColumnas.add(new Columna("CANTIDAD_OBRAS", false, false));
    }

    @Override
    public Integer insertar(AutorDTO autor) {
        this.autor = autor;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setString(1, this.autor.getNombre());
            this.statement.setString(2, this.autor.getNacionalidad());
            this.statement.setInt(3, this.autor.getActivo() ? 1 : 0);
            this.statement.setInt(4, this.autor.getCantidadObras());
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setString(1, this.autor.getNombre());
        this.statement.setString(2, this.autor.getNacionalidad());
        this.statement.setInt(3, this.autor.getActivo() ? 1 : 0);
        this.statement.setInt(4, this.autor.getCantidadObras());
        this.statement.setInt(5, this.autor.getIdAutor());
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
        this.autor = new AutorDTO();
        autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
        autor.setNombre(this.resultSet.getString("NOMBRE_COMPLETO"));
        autor.setNacionalidad(this.resultSet.getString("NACIONALIDAD"));
        autor.setActivo(this.resultSet.getInt("ACTIVO") == 1);
        autor.setCantidadObras(this.resultSet.getInt("CANTIDAD_OBRAS"));
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
    public AutorDTO obtenerPorId(Integer idAutor) {
        this.autor = new AutorDTO();
        this.autor.setIdAutor(idAutor);
        super.obtenerPorId();
        return this.autor;
    }

    @Override
    public ArrayList<AutorDTO> listarTodos() {
        return (ArrayList<AutorDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(AutorDTO autor) {
        this.autor = autor;
        return super.modificar();
    }

    @Override
    public Integer eliminar(AutorDTO autor) {
        this.autor = autor;
        return super.eliminar();
    }

}
