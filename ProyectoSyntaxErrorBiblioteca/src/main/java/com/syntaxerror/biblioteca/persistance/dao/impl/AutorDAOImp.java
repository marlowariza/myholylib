package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.AutorDTO;
import com.syntaxerror.biblioteca.persistance.dao.AutorDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorDAOImp extends DAOImplBase implements AutorDAO {

    private AutorDTO autor;

    public AutorDAOImp() {
        super("myholylib.Autor");
        this.retornarLlavePrimaria = true;
        this.autor = null;
    }

    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas = new ArrayList<>();
        this.listaColumnas.add(new Columna("IdAutor", true, false));
        this.listaColumnas.add(new Columna("Nombre", false, false));
        this.listaColumnas.add(new Columna("Nacionalidad", false, false));
        this.listaColumnas.add(new Columna("Activo", false, false));
        this.listaColumnas.add(new Columna("CantidadObras", false, false));
    }

    @Override
    public Integer insertar(AutorDTO autor) {
        this.autor = autor;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            // Agregar el ID manualmente ya que no es AUTO_INCREMENT
            this.statement.setInt(1, autor.getIdAutor());
            this.statement.setString(2, autor.getNombre());
            this.statement.setString(3, autor.getNacionalidad());
            this.statement.setInt(4, autor.getActivo() ? 1 : 0);
            this.statement.setInt(5, autor.getCantidadObras());
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImp.class.getName()).log(Level.SEVERE, "Error al asignar valores al statement", ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {
            this.statement.setInt(5, autor.getIdAutor());
            this.statement.setString(1, autor.getNombre());
            this.statement.setString(2, autor.getNacionalidad());
            this.statement.setInt(3, autor.getActivo() ? 1 : 0);
            this.statement.setInt(4, autor.getCantidadObras());
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() {
        try {
            this.statement.setInt(1, autor.getIdAutor());
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AutorDTO obtenerPorId(Integer id) {
        AutorDTO autor = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                autor = new AutorDTO();
                autor.setIdAutor(this.resultSet.getInt("IdAutor"));
                autor.setNombre(this.resultSet.getString("Nombre"));
                autor.setNacionalidad(this.resultSet.getString("Nacionalidad"));
                autor.setActivo(this.resultSet.getInt("Activo") == 1);
                autor.setCantidadObras(this.resultSet.getInt("CantidadObras"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AutorDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return autor;
    }

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
                autor.setIdAutor(this.resultSet.getInt("IdAutor"));
                autor.setNombre(this.resultSet.getString("Nombre"));
                autor.setNacionalidad(this.resultSet.getString("Nacionalidad"));
                autor.setActivo(this.resultSet.getInt("Activo") == 1);
                autor.setCantidadObras(this.resultSet.getInt("CantidadObras"));
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
//        int resultado = 0;
//        try {
//            this.conexion = DBManager.getInstance().getConnection();
//            this.conexion.setAutoCommit(false);
//            String sql = this.generarSQLParaModificacion();
//            this.statement = this.conexion.prepareCall(sql);
//            this.statement.setString(1, autor.getNombre());
//            this.statement.setString(2, autor.getNacionalidad());
//            this.statement.setInt(3, autor.getActivo() ? 1 : 0);
//            this.statement.setInt(4, autor.getCantidadObras());
//            this.statement.setInt(5, autor.getIdAutor());
//            resultado = this.statement.executeUpdate();
//            this.conexion.commit();
//        } catch (SQLException ex) {
//            System.err.println("Error al intentar modificar - " + ex);
//            try {
//                if (this.conexion != null) {
//                    this.conexion.rollback();
//                }
//            } catch (SQLException ex1) {
//                System.err.println("Error al hacer rollback - " + ex1);
//            }
//        } finally {
//            try {
//                if (this.conexion != null) {
//                    this.conexion.close();
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error al cerrar la conexión - " + ex);
//            }
//        }
//        return resultado;
        this.autor=autor;
        return super.modificar();
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
