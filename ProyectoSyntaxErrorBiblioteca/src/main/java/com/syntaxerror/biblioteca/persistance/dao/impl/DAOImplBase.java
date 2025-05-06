package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Tipo_Operacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAOImplBase {

    protected String nombreTabla;
    protected ArrayList<Columna> listaColumnas;
    protected Boolean retornarLlavePrimaria;
    protected Boolean mostrarSentenciaSQL;
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;

    public DAOImplBase(String nombreTabla) {
        this.nombreTabla = nombreTabla;
        this.retornarLlavePrimaria = false;
        this.mostrarSentenciaSQL = true;
        this.incluirListaDeColumnas();
    }

    private void incluirListaDeColumnas() {
        this.listaColumnas = new ArrayList<>();
        this.configurarListaDeColumnas();
    }

    protected abstract void configurarListaDeColumnas();

    protected void abrirConexion() {
        this.conexion = DBManager.getInstance().getConnection();
    }

    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
            this.conexion = null;
        }
    }

    protected void iniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected void colocarSQLenStatement(String sql) throws SQLException {
        this.statement = this.conexion.prepareCall(sql);
    }

    protected Integer ejecutarModificacionEnBD() throws SQLException {
        if (this.mostrarSentenciaSQL) {
            Logger.getLogger(DAOImplBase.class.getName()).log(Level.INFO, this.statement.toString());
        }
        return this.statement.executeUpdate();
    }

    protected void ejecutarConsultaEnBD() throws SQLException {
        this.resultSet = this.statement.executeQuery();
    }

    protected Integer insertar() {
        return this.ejecuta_DML(Tipo_Operacion.INSERTAR);
    }

    protected Integer modificar() {
        return this.ejecuta_DML(Tipo_Operacion.MODIFICAR);
    }

    protected Integer eliminar() {
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR);
    }

    private Integer ejecuta_DML(Tipo_Operacion tipo_Operacion) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            // this.conexion = DBManager.getInstance().getConnection();
            // this.conexion.setAutoCommit(false);
            String sql = null;
            switch (tipo_Operacion) {
                case Tipo_Operacion.INSERTAR ->
                    sql = this.generarSQLParaInsercion();
                case Tipo_Operacion.MODIFICAR ->
                    sql = this.generarSQLParaModificacion();
                case Tipo_Operacion.ELIMINAR ->
                    sql = this.generarSQLParaEliminacion();
            }
            this.colocarSQLenStatement(sql);
            switch (tipo_Operacion) {
                case Tipo_Operacion.INSERTAR ->
                    this.incluirValorDeParametrosParaInsercion();
                case Tipo_Operacion.MODIFICAR ->
                    this.incluirValorDeParametrosParaModificacion();
                case Tipo_Operacion.ELIMINAR ->
                    this.incluirValorDeParametrosParaEliminacion();
            }
            resultado = this.ejecutarModificacionEnBD();
            if (this.retornarLlavePrimaria && tipo_Operacion == Tipo_Operacion.INSERTAR) {
                resultado = this.retornarUltimoAutoGenerado();
            }
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar consulta - " + tipo_Operacion.toString() + ": " + ex);
            try {
                this.rollbackTransaccion();
                // if (this.conexion != null) {
                // this.conexion.rollback();
                // }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex);
            }
        } finally {
            try {
                this.cerrarConexion();
                // if (this.conexion != null) {
                // this.conexion.close();
                // }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    protected String generarSQLParaInsercion() {
        // sentencia SQL a generar es similar a
        // INSERT INTO INV_ALMACENES (NOMBRE, ALMACEN_CENTRAL) VALUES (?,?)
        String sql = "INSERT INTO ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat("(");
        String sql_columnas = "";
        String sql_parametros = "";
        for (Columna columna : this.listaColumnas) {
            if (!columna.getEsAutoGenerado()) {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                    sql_parametros = sql_parametros.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_parametros = sql_parametros.concat("?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(") VALUES (");
        sql = sql.concat(sql_parametros);
        sql = sql.concat(")");
        return sql;
    }

    protected String generarSQLParaModificacion() {
        // sentencia SQL a generar es similar a
        // UPDATE INV_ALMACENES SET NOMBRE=?, ALMACEN_CENTRAL=? WHERE ALMACEN_ID=?
        String sql = "UPDATE ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" SET ");
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsllavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            } else {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_columnas = sql_columnas.concat("=?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaEliminacion() {
        // sentencia SQL a generar es similar a
        // DELETE FROM INV_ALMACENES WHERE ALMACEN_ID=?
        String sql = "DELETE FROM ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" WHERE ");
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsllavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
        }
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaObtenerPorId() {
        // sentencia SQL a generar es similar a
        // SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES WHERE
        // ALMACEN_ID = ?
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsllavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaListarTodos() {
        // sentencia SQL a generar es similar a
        // SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES
        String sql = "SELECT ";
        String sql_columnas = "";
        for (Columna columna : this.listaColumnas) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombreTabla);
        return sql;
    }

    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    public Integer retornarUltimoAutoGenerado() {
        Integer resultado = null;
        try {
            String sql = "select @@last_insert_id as id";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                resultado = this.resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar retornarUltimoAutoGenerado - " + ex);
        }
        return resultado;
    }

    public void obtenerPorId() {
        try {
            this.abrirConexion();
            String sql = this.generarSQLParaObtenerPorId();
            this.colocarSQLenStatement(sql);
            this.incluirValorDeParametrosParaObtenerPorId();
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
    }

    public List listarTodos() {
        List lista = new ArrayList<>();
        try {
            this.abrirConexion();
            String sql = this.generarSQLParaListarTodos();
            this.colocarSQLenStatement(sql);
            this.ejecutarConsultaEnBD();
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }

    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void agregarObjetoALaLista(List lista) throws SQLException {
        throw new UnsupportedOperationException("Método no sobreescrito.");
    }

    protected void ejecutarProcedimientoAlmacenado(String sql, Boolean conTransaccion) {
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        this.ejecutarProcedimientoAlmacenado(sql, incluirValorDeParametros, parametros, conTransaccion);
    }

    protected void ejecutarProcedimientoAlmacenado(String sql, Consumer incluirValorDeParametros, Object parametros,
            Boolean conTransaccion) {
        try {
            if (conTransaccion) {
                this.iniciarTransaccion();
            } else {
                this.abrirConexion();
            }
            this.colocarSQLenStatement(sql);
            if (incluirValorDeParametros != null) {
                incluirValorDeParametros.accept(parametros);
            }
            this.ejecutarModificacionEnBD();
            if (conTransaccion) {
                this.comitarTransaccion();
            }
        } catch (SQLException ex) {
            if (conTransaccion)
                try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImplBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImplBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected Integer insertarPersona(
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String contrasenha,
            Integer sedeId
    ) throws SQLException {
        iniciarTransaccion();
        Integer nextId;

        try {
            // Cálculo de próximo ID
            String seqSql = "SELECT COALESCE(MAX(ID_PERSONA), 0) + 1 FROM BIB_PERSONA";
            try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(seqSql)) {
                rs.next();
                nextId = rs.getInt(1);
            }

            // INSERT con ese ID
            String insertSql = """
            INSERT INTO BIB_PERSONA
              (ID_PERSONA, NOMBRE_COMPLETO, DIRECCION, TELEFONO, CORREO, CONTRASENHA, SEDE_IDSEDE)
            VALUES
              (?, ?, ?, ?, ?, ?, ?)
        """;
            try (PreparedStatement ps = conexion.prepareStatement(insertSql)) {
                ps.setInt(1, nextId);
                ps.setString(2, nombre);
                ps.setString(3, direccion);
                ps.setString(4, telefono);
                ps.setString(5, correo);
                ps.setString(6, contrasenha);
                ps.setInt(7, sedeId);
                ps.executeUpdate();
                comitarTransaccion();
            }

            return nextId;
        } finally {
            cerrarConexion();
        }
    }

// 2) MODIFICAR usando el ID que ya tienes
    protected boolean modificarPersona(
            Integer idPersona,
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String contrasenha,
            Integer sedeId
    ) throws SQLException {
        abrirConexion();
        try {
            String sql = """
            UPDATE BIB_PERSONA
               SET NOMBRE_COMPLETO = ?,
                   DIRECCION        = ?,
                   TELEFONO         = ?,
                   CORREO           = ?,
                   CONTRASENHA      = ?,
                   SEDE_IDSEDE      = ?
             WHERE ID_PERSONA      = ?
        """;
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, direccion);
                ps.setString(3, telefono);
                ps.setString(4, correo);
                ps.setString(5, contrasenha);
                ps.setInt(6, sedeId);
                ps.setInt(7, idPersona);
                return ps.executeUpdate() > 0;
            }
           
        } 
      
        finally {
            cerrarConexion();
        }
    }

// 3) ELIMINAR la persona asociada a un lector (clave foránea)
    protected void eliminarPersonaByLector(Integer idLector) throws SQLException {
        iniciarTransaccion();
        try {
            // 3.1) Recuperar FK persona_id
            Integer personaId = null;
            String sel = "SELECT PERSONA_IDPERSONA FROM BIB_LECTOR WHERE ID_LECTOR = ?";
            try (PreparedStatement ps1 = conexion.prepareStatement(sel)) {
                ps1.setInt(1, idLector);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) {
                        personaId = rs.getInt(1);
                    }
                }
            }
            // 3.2) Borrar persona si existe
            if (personaId != null) {
                String del = "DELETE FROM BIB_PERSONA WHERE ID_PERSONA = ?";
                try (PreparedStatement ps2 = conexion.prepareStatement(del)) {
                    ps2.setInt(1, personaId);
                    ps2.executeUpdate();
                }
            }
            comitarTransaccion();
        } finally {
            cerrarConexion();
        }
    }
//    protected Integer insertarPersona(
//            String nombre,
//            String direccion,
//            String telefono,
//            String correo,
//            String contrasenha,
//            Integer sedeId
//    ) throws SQLException {
//        abrirConexion();
//        String sql = """
//        INSERT INTO BIB_PERSONA
//          (ID_PERSONA, NOMBRE_COMPLETO, DIRECCION, TELEFONO, CORREO, CONTRASENHA, SEDE_IDSEDE)
//        VALUES
//          (?, ?, ?, ?, ?, ?, ?)
//    """;
//        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setString(1, nombre);
//            ps.setString(2, direccion);
//            ps.setString(3, telefono);
//            ps.setString(4, correo);
//            ps.setString(5, contrasenha);
//            ps.setInt(6, sedeId);
//            ps.executeUpdate();
//            try (ResultSet keys = ps.getGeneratedKeys()) {
//                return keys.next() ? keys.getInt(1) : null;
//            }
//        } finally {
//            cerrarConexion();
//        }
//    }
//
//    protected boolean modificarPersona(
//            Integer idPersona,
//            String nombre,
//            String direccion,
//            String telefono,
//            String correo,
//            String contrasenha,
//            Integer sedeId
//    ) throws SQLException {
//        abrirConexion();
//        String sql = """
//        UPDATE BIB_PERSONA
//           SET NOMBRE_COMPLETO=?, DIRECCION=?, TELEFONO=?, CORREO=?, CONTRASENHA=?, SEDE_IDSEDE=?
//         WHERE ID_PERSONA=?
//    """;
//        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
//            ps.setString(1, nombre);
//            ps.setString(2, direccion);
//            ps.setString(3, telefono);
//            ps.setString(4, correo);
//            ps.setString(5, contrasenha);
//            ps.setInt(6, sedeId);
//            ps.setInt(7, idPersona);
//            return ps.executeUpdate() > 0;
//        } finally {
//            cerrarConexion();
//        }
//    }
//
//    protected void eliminarPersonaByLector(Integer idLector) throws SQLException {
//        abrirConexion();
//        // 1) obtengo persona_id
//        String sel = "SELECT PERSONA_IDPERSONA FROM BIB_LECTOR WHERE ID_LECTOR = ?";
//        Integer personaId = null;
//        try (PreparedStatement ps1 = conexion.prepareStatement(sel)) {
//            ps1.setInt(1, idLector);
//            try (ResultSet rs = ps1.executeQuery()) {
//                if (rs.next()) {
//                    personaId = rs.getInt(1);
//                }
//            }
//        }
//        // 2) si existe, borro
//        if (personaId != null) {
//            String del = "DELETE FROM BIB_PERSONA WHERE ID_PERSONA = ?";
//            try (PreparedStatement ps2 = conexion.prepareStatement(del)) {
//                ps2.setInt(1, personaId);
//                ps2.executeUpdate();
//            }
//        }
//        cerrarConexion();
//    }

    // 🔹 Métodos de PERSONA
//    protected Integer insertarPersona(String nombre, String direccion, String telefono, String correo, String contrasenha, Integer sedeId) throws SQLException {
//        String sql = "INSERT INTO BIB_PERSONA (NOMBRE_COMPLETO, DIRECCION, TELEFONO, CORREO, CONTRASENHA, SEDE_IDSEDE) VALUES (?, ?, ?, ?, ?, ?)";
//         abrirConexion();
//        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            incluirParametrosPersona(statement, nombre, direccion, telefono, correo, contrasenha, sedeId);
//            int filasAfectadas = statement.executeUpdate();
//
//            if (filasAfectadas > 0) {
//                ResultSet keys = statement.getGeneratedKeys();
//                if (keys.next()) {
//                    return keys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        cerrarConexion();
//        return null;
//    }
//
//    // 🔹 Método para incluir parámetros en la inserción de persona
//    protected void incluirParametrosPersona(PreparedStatement statement, String nombre, String direccion, String telefono, String correo, String contrasenha, Integer sedeId) throws SQLException {
//        statement.setString(1, nombre);
//        statement.setString(2, direccion);
//        statement.setString(3, telefono);
//        statement.setString(4, correo);
//        statement.setString(5, contrasenha);
//        statement.setInt(6, sedeId);
//    }
//
//    protected boolean modificarPersona(Integer idPersona, String nombre, String direccion,
//            String telefono, String correo, String contrasenha,
//            Integer sedeId) throws SQLException {
//        String sql = "UPDATE BIB_PERSONA SET NOMBRE_COMPLETO=?, DIRECCION=?, TELEFONO=?, CORREO=?, CONTRASENHA=?, SEDE_IDSEDE=? WHERE IDPERSONA=?";
//         abrirConexion();
//        try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//            ps.setString(1, nombre);
//            ps.setString(2, direccion);
//            ps.setString(3, telefono);
//            ps.setString(4, correo);
//            ps.setString(5, contrasenha);
//            ps.setInt(6, sedeId);
//            ps.setInt(7, idPersona);
//            cerrarConexion();
//            return ps.executeUpdate() > 0;
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            cerrarConexion();
//            return false;
//        }
//    }
//
//    protected void eliminarPersonaByLector(Integer idLector) throws SQLException {
//        // Primero obtener persona_id
//        Integer personaId = null;
//        String sel = "SELECT PERSONA_IDPERSONA FROM BIB_LECTOR WHERE ID_LECTOR = ?";
//         abrirConexion();
//        try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps1 = c.prepareStatement(sel)) {
//            ps1.setInt(1, idLector);
//            try (ResultSet rs = ps1.executeQuery()) {
//                if (rs.next()) {
//                    personaId = rs.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (personaId != null) {
//            String sql = "DELETE FROM BIB_PERSONA WHERE IDPERSONA = ?";
//            try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps2 = c.prepareStatement(sql)) {
//                ps2.setInt(1, personaId);
//                ps2.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        cerrarConexion();
//    }
}
