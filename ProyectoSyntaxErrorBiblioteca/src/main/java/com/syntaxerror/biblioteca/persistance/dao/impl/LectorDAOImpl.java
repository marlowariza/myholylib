package com.syntaxerror.biblioteca.persistance.dao.impl;
import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.LectorDTO;
import com.syntaxerror.biblioteca.persistance.dao.LectorDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LectorDAOImpl extends DAOImplBase implements LectorDAO {
    private LectorDTO lector;
    
    public LectorDAOImpl() {
        super("myholylib.Lector");
        this.retornarLlavePrimaria = true;
        this.lector = null;
    }
    
    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas = new ArrayList<>();
        this.listaColumnas.add(new Columna("IdPersona", true, false));
        this.listaColumnas.add(new Columna("FechaNacimiento", false, false));
        this.listaColumnas.add(new Columna("Nombre", false, false));
        this.listaColumnas.add(new Columna("Direccion", false, false));
        this.listaColumnas.add(new Columna("Telefono", false, false));
        this.listaColumnas.add(new Columna("Email", false, false));
        this.listaColumnas.add(new Columna("Contrasenha", false, false));
        this.listaColumnas.add(new Columna("Categoria", false, false));
        this.listaColumnas.add(new Columna("CantidadPrestamos", false, false));
        this.listaColumnas.add(new Columna("CantidadPenalizaciones", false, false));
    }
    
    @Override
    public Integer insertar(LectorDTO lector) {
        this.lector = lector;
        
        // Insertar en la tabla Persona
        Integer personaId = insertarPersona();
        
        // Establecer el ID de Persona para el Lector
        this.lector.setIdPersona(personaId);
        
        // Insertar en la tabla Lector
        return super.insertar();
    }
    
    private Integer insertarPersona() {
        Integer idPersonaGenerado = null;
        try {
            // Insertar datos comunes en la tabla Persona
            String sql = "INSERT INTO Persona (FechaNacimiento, Nombre, Direccion, Telefono, Email, Contrasenha) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            this.statement = (CallableStatement) this.conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  // Usamos RETURN_GENERATED_KEYS para obtener el ID auto-generado
            
            this.statement.setDate(1, new java.sql.Date(lector.getFechaNacimiento().getTime()));
            this.statement.setString(2, lector.getNombre());
            this.statement.setString(3, lector.getDireccion());
            this.statement.setString(4, lector.getTelefono());
            this.statement.setString(5, lector.getEmail());
            this.statement.setString(6, lector.getContrasenha());
            
            int filasAfectadas = this.statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado para Persona
                ResultSet generatedKeys = this.statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPersonaGenerado = generatedKeys.getInt(1);  // Obtener el ID auto-generado
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPersonaGenerado;
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setInt(1, lector.getIdPersona()); // Referencia a la tabla Persona
            this.statement.setInt(2, lector.getCategoria());
            this.statement.setInt(3, lector.getCantidadPrestamos());
            this.statement.setInt(4, lector.getCantidadPenalizaciones());
        } catch (SQLException ex) {
            Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    
    
    @Override
    public LectorDTO obtenerPorId(Integer id) {
        LectorDTO lector = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                lector = new LectorDTO();
                lector.setIdPersona(this.resultSet.getInt("IdPersona"));
                lector.setFechaNacimiento(this.resultSet.getDate("FechaNacimiento"));
                lector.setNombre(this.resultSet.getString("Nombre"));
                lector.setDireccion(this.resultSet.getString("Direccion"));
                lector.setTelefono(this.resultSet.getString("Telefono"));
                lector.setEmail(this.resultSet.getString("Email"));
                lector.setContrasenha(this.resultSet.getString("Contrasenha"));
                lector.setCategoria(this.resultSet.getInt("Categoria"));
                lector.setCantidadPrestamos(this.resultSet.getInt("CantidadPrestamos"));
                lector.setCantidadPenalizaciones(this.resultSet.getInt("CantidadPenalizaciones"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lector;
    }

    @Override
    public ArrayList<LectorDTO> listarTodos() {
        ArrayList<LectorDTO> listaLectores = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                LectorDTO lector = new LectorDTO();
                lector.setIdPersona(this.resultSet.getInt("IdPersona"));
                lector.setFechaNacimiento(this.resultSet.getDate("FechaNacimiento"));
                lector.setNombre(this.resultSet.getString("Nombre"));
                lector.setDireccion(this.resultSet.getString("Direccion"));
                lector.setTelefono(this.resultSet.getString("Telefono"));
                lector.setEmail(this.resultSet.getString("Email"));
                lector.setContrasenha(this.resultSet.getString("Contrasenha"));
                lector.setCategoria(this.resultSet.getInt("Categoria"));
                lector.setCantidadPrestamos(this.resultSet.getInt("CantidadPrestamos"));
                lector.setCantidadPenalizaciones(this.resultSet.getInt("CantidadPenalizaciones"));
                listaLectores.add(lector);
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
        return listaLectores;
    }

    @Override
    public Integer modificar(LectorDTO lector) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setString(1, lector.getNombre());
            this.statement.setString(2, lector.getDireccion());
            this.statement.setString(3, lector.getTelefono());
            this.statement.setString(4, lector.getEmail());
            this.statement.setString(5, lector.getContrasenha());
            this.statement.setInt(6, lector.getCategoria());
            this.statement.setInt(7, lector.getCantidadPrestamos());
            this.statement.setInt(8, lector.getCantidadPenalizaciones());
            this.statement.setInt(9, lector.getIdPersona());
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
    public Integer eliminar(LectorDTO lector) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, lector.getIdPersona());
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
