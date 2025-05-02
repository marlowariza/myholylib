package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;

public abstract class RelacionDAOImplBase extends DAOImplBase {
    protected String columnaId1;
    protected String columnaId2;

    public RelacionDAOImplBase(String nombreTabla, String columnaId1, String columnaId2) {
        super(nombreTabla);
        this.columnaId1 = columnaId1;
        this.columnaId2 = columnaId2;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas = new ArrayList<>();
        this.listaColumnas.add(new Columna(columnaId1, true, false));
        this.listaColumnas.add(new Columna(columnaId2, true, false));
    }

    // Métodos específicos para relaciones
    public int crearRelacion(Integer id1, Integer id2) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id1);
            this.statement.setInt(2, id2);
            resultado = this.ejecutarModificacionEnBD();
            this.comitarTransaccion();
        } catch (SQLException ex) {
            Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    public int eliminarRelacion(Integer id1, Integer id2) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id1);
            this.statement.setInt(2, id2);
            resultado = this.ejecutarModificacionEnBD();
            this.comitarTransaccion();
        } catch (SQLException ex) {
            Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    public boolean existeRelacion(Integer id1, Integer id2) {
        boolean existe = false;
        try {
            this.abrirConexion();
            String sql = String.format("SELECT 1 FROM %s WHERE %s = ? AND %s = ?",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id1);
            this.statement.setInt(2, id2);
            this.ejecutarConsultaEnBD();
            existe = this.resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return existe;
    }

    public List<Integer> buscarRelacionadosPorId1(Integer id) {
        return buscarRelacionados(id, columnaId1, columnaId2);
    }

    public List<Integer> buscarRelacionadosPorId2(Integer id) {
        return buscarRelacionados(id, columnaId2, columnaId1);
    }

    // refactoriza buscarRelacionadosPorId1 y buscarRelacionadosPorId1,
    // parametrizando que columna se filtra
    private List<Integer> buscarRelacionados(Integer id, String columnaFiltro, String columnaResultado) {
        List<Integer> resultados = new ArrayList<>();
        try {
            this.abrirConexion();
            String sql = String.format("SELECT %s FROM %s WHERE %s = ?",
                    columnaResultado, nombreTabla, columnaFiltro);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id);
            this.ejecutarConsultaEnBD();
            while (this.resultSet.next()) {
                resultados.add(this.resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(RelacionDAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultados;
    }

    // Métodos abstractos requeridos por DAOImplBase
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        // No se usa
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        // No se usa
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        // No se usa
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        // No se usa
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        // No se usa
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        // No se usa
    }
}
