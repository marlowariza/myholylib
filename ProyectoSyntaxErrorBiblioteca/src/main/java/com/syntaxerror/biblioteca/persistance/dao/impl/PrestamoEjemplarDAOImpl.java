package com.syntaxerror.biblioteca.persistance.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.syntaxerror.biblioteca.model.PrestamoEjemplarDTO;
import com.syntaxerror.biblioteca.model.enums.EstadoPrestamoEjemplar;
import com.syntaxerror.biblioteca.persistance.dao.PrestamoEjemplarDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;

public class PrestamoEjemplarDAOImpl extends RelacionDAOImplBase implements PrestamoEjemplarDAO {
    public PrestamoEjemplarDAOImpl() {
        super("BIB_PRESTAMO_EJEMPLAR", "ID_PRESTAMO", "ID_EJEMPLAR");
    }

    @Override
    protected void configurarListaDeColumnas() {
        super.configurarListaDeColumnas();
        this.listaColumnas.add(new Columna("FECHA_DEVOLUCION_REAL", false, false));
        this.listaColumnas.add(new Columna("ESTADO", false, false));
    }

    @Override
    public Integer insertar(PrestamoEjemplarDTO prestamoEjemplar) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = String.format("INSERT INTO %s (%s, %s, FECHA_DEVOLUCION_REAL, ESTADO) VALUES (?, ?, ?, ?)",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, prestamoEjemplar.getIdPrestamo());
            this.statement.setInt(2, prestamoEjemplar.getIdEjemplar());
            this.statement.setDate(3, new Date(prestamoEjemplar.getFechaRealDevolucion().getTime()));
            this.statement.setString(4, prestamoEjemplar.getEstado().name());
            resultado = this.ejecutarModificacionEnBD();
            this.comitarTransaccion();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public Integer modificar(PrestamoEjemplarDTO prestamoEjemplar) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = String.format("UPDATE %s SET FECHA_DEVOLUCION_REAL = ?, ESTADO = ? WHERE %s = ? AND %s = ?",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setDate(1, new Date(prestamoEjemplar.getFechaRealDevolucion().getTime()));
            this.statement.setString(2, prestamoEjemplar.getEstado().name());
            this.statement.setInt(3, prestamoEjemplar.getIdPrestamo());
            this.statement.setInt(4, prestamoEjemplar.getIdEjemplar());
            resultado = this.ejecutarModificacionEnBD();
            this.comitarTransaccion();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public Integer eliminar(Integer idPrestamo, Integer idEjemplar) {
        return super.eliminarRelacion(idPrestamo, idEjemplar);
    }

    @Override
    public PrestamoEjemplarDTO buscarPorIds(Integer idPrestamo, Integer idEjemplar) {
        PrestamoEjemplarDTO prestamoEjemplar = null;
        try {
            this.abrirConexion();
            String sql = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?",
                    nombreTabla, columnaId1, columnaId2);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idPrestamo);
            this.statement.setInt(2, idEjemplar);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                prestamoEjemplar = mapearResultSetADTO();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prestamoEjemplar;
    }

    @Override
    public List<PrestamoEjemplarDTO> buscarPorPrestamo(Integer idPrestamo) {
        return buscarRelacionados(idPrestamo, columnaId1);
    }

    @Override
    public List<PrestamoEjemplarDTO> buscarPorEjemplar(Integer idEjemplar) {
        return buscarRelacionados(idEjemplar, columnaId2);
    }

    @Override
    public boolean existeRelacion(Integer idPrestamo, Integer idEjemplar) {
        return super.existeRelacion(idPrestamo, idEjemplar);
    }

    private List<PrestamoEjemplarDTO> buscarRelacionados(Integer id, String columnaFiltro) {
        List<PrestamoEjemplarDTO> resultados = new ArrayList<>();
        try {
            this.abrirConexion();
            String sql = String.format("SELECT * FROM %s WHERE %s = ?", nombreTabla, columnaFiltro);
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, id);
            this.ejecutarConsultaEnBD();
            while (this.resultSet.next()) {
                resultados.add(mapearResultSetADTO());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoEjemplarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultados;
    }

    private PrestamoEjemplarDTO mapearResultSetADTO() throws SQLException {
        PrestamoEjemplarDTO dto = new PrestamoEjemplarDTO();
        dto.setIdPrestamo(this.resultSet.getInt(columnaId1));
        dto.setIdEjemplar(this.resultSet.getInt(columnaId2));
        dto.setFechaRealDevolucion(this.resultSet.getDate("FECHA_DEVOLUCION_REAL"));
        dto.setEstado(EstadoPrestamoEjemplar.valueOf(this.resultSet.getString("ESTADO")));
        return dto;
    }
}
