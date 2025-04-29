package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.AutorDTO;
import com.syntaxerror.biblioteca.persistance.dao.AutorDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;

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
            this.statement.setInt(3, this.autor.getActivo()?1:0);
            this.statement.setInt(4, this.autor.getCantidadObras());
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AutorDTO obtenerPorId(Integer autorId) {
        AutorDTO autor = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, autorId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                autor = new AutorDTO();
                autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
                autor.setNombre(this.resultSet.getString("NOMBRE_COMPLETO"));
                autor.setNacionalidad(this.resultSet.getString("NACIONALIDAD"));
                autor.setActivo(this.resultSet.getInt("ACTIVO") == 1);
                autor.setCantidadObras(this.resultSet.getInt("CANTIDAD_OBRAS"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return autor;
    }
//--------------------------------
    @Override
    public ArrayList<AutorDTO> listarTodos() {
        ArrayList<AutorDTO> listaAutores = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                AutorDTO autor = new AutorDTO();
                autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
                autor.setNombre(this.resultSet.getString("NOMBRE_COMPLETO"));
                autor.setNacionalidad(this.resultSet.getString("NACIONALIDAD"));
                autor.setActivo(this.resultSet.getInt("ACTIVO") == 1);
                autor.setCantidadObras(this.resultSet.getInt("CANTIDAD_OBRAS"));
                listaAutores.add(autor);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return listaAutores;
    }

    @Override
    public Integer modificar(AutorDTO autor) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setString(1, this.autor.getNombre());
            this.statement.setString(2, this.autor.getNacionalidad());
            this.statement.setInt(3, this.autor.getActivo()?1:0);
            this.statement.setInt(4, this.autor.getCantidadObras());
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar modificar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    @Override
    public Integer eliminar(AutorDTO autor) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, autor.getIdAutor());
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar eliminar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

}