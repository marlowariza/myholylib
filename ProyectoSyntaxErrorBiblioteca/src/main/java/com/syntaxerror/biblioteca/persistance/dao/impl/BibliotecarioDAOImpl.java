/*
 * DAO Implementation for Bibliotecario
 */
package com.syntaxerror.biblioteca.persistance.dao.impl;
import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.BibliotecarioDTO;
import com.syntaxerror.biblioteca.persistance.dao.BibliotecarioDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import com.syntaxerror.biblioteca.model.enums.Turnos;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BibliotecarioDAOImpl extends DAOImplBase implements BibliotecarioDAO {
    private BibliotecarioDTO bibliotecario;
    
    public BibliotecarioDAOImpl() {
        super("myholylib.Bibliotecario");
        this.retornarLlavePrimaria = true;
        this.bibliotecario = null;
    }
    
    @Override
    protected void configurarListaDeColumna() {
        this.listaColumnas = new ArrayList<>();
        this.listaColumnas.add(new Columna("IdPersona", true, true));
        this.listaColumnas.add(new Columna("FechaNacimiento", false, false));
        this.listaColumnas.add(new Columna("Nombre", false, false));
        this.listaColumnas.add(new Columna("Direccion", false, false));
        this.listaColumnas.add(new Columna("Telefono", false, false));
        this.listaColumnas.add(new Columna("Email", false, false));
        this.listaColumnas.add(new Columna("Contrasenha", false, false));
        this.listaColumnas.add(new Columna("Turno", false, false));
        this.listaColumnas.add(new Columna("FechaContrato", false, false));
    }
    
    @Override
    public Integer insertar(BibliotecarioDTO bibliotecario) {
        this.bibliotecario = bibliotecario;
        
        // Insertar en la tabla Persona
        Integer personaId = insertarPersona();  // Inserta en la tabla Persona y devuelve el ID generado
        
        // Establecer el ID de Persona para el Bibliotecario
        this.bibliotecario.setIdPersona(personaId);
        
        // Insertar en la tabla Bibliotecario
        return super.insertar();
    }
    
    private Integer insertarPersona() {
        Integer idPersonaGenerado = null;
        try {
            // Insertar datos comunes en la tabla Persona
            String sql = "INSERT INTO Persona (FechaNacimiento, Nombre, Direccion, Telefono, Email, Contrasenha) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            this.statement = (CallableStatement) this.conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  // Usamos RETURN_GENERATED_KEYS para obtener el ID auto-generado
            
            this.statement.setDate(1, new java.sql.Date(bibliotecario.getFechaNacimiento().getTime()));
            this.statement.setString(2, bibliotecario.getNombre());
            this.statement.setString(3, bibliotecario.getDireccion());
            this.statement.setString(4, bibliotecario.getTelefono());
            this.statement.setString(5, bibliotecario.getEmail());
            this.statement.setString(6, bibliotecario.getContrasenha());
            
            int filasAfectadas = this.statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado para Persona
                ResultSet generatedKeys = this.statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPersonaGenerado = generatedKeys.getInt(1);  // Obtener el ID auto-generado
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPersonaGenerado;
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setInt(1, bibliotecario.getIdPersona()); // Referencia a la tabla Persona
            this.statement.setString(2, bibliotecario.getTurno().name());  // Suponiendo que "Turnos" es un enum
            this.statement.setDate(3, new java.sql.Date(bibliotecario.getFechaContrato().getTime()));
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BibliotecarioDTO obtenerPorId(Integer id) {
        BibliotecarioDTO bibliotecario = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                bibliotecario = new BibliotecarioDTO();
                bibliotecario.setIdPersona(this.resultSet.getInt("IdPersona"));
                bibliotecario.setFechaNacimiento(this.resultSet.getDate("FechaNacimiento"));
                bibliotecario.setNombre(this.resultSet.getString("Nombre"));
                bibliotecario.setDireccion(this.resultSet.getString("Direccion"));
                bibliotecario.setTelefono(this.resultSet.getString("Telefono"));
                bibliotecario.setEmail(this.resultSet.getString("Email"));
                bibliotecario.setContrasenha(this.resultSet.getString("Contrasenha"));
                bibliotecario.setTurno(Turnos.valueOf(this.resultSet.getString("Turno").trim().toUpperCase()));
                bibliotecario.setFechaContrato(this.resultSet.getDate("FechaContrato"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BibliotecarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bibliotecario;
    }

    @Override
    public ArrayList<BibliotecarioDTO> listarTodos() {
        ArrayList<BibliotecarioDTO> listaBibliotecarios = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                BibliotecarioDTO bibliotecario = new BibliotecarioDTO();
                bibliotecario.setIdPersona(this.resultSet.getInt("IdPersona"));
                bibliotecario.setFechaNacimiento(this.resultSet.getDate("FechaNacimiento"));
                bibliotecario.setNombre(this.resultSet.getString("Nombre"));
                bibliotecario.setDireccion(this.resultSet.getString("Direccion"));
                bibliotecario.setTelefono(this.resultSet.getString("Telefono"));
                bibliotecario.setEmail(this.resultSet.getString("Email"));
                bibliotecario.setContrasenha(this.resultSet.getString("Contrasenha"));
                bibliotecario.setTurno(Turnos.valueOf(this.resultSet.getString("Turno").trim().toUpperCase()));
                bibliotecario.setFechaContrato(this.resultSet.getDate("FechaContrato"));
                listaBibliotecarios.add(bibliotecario);
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
        return listaBibliotecarios;
    }

    @Override
    public Integer modificar(BibliotecarioDTO bibliotecario) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setString(1, bibliotecario.getNombre());
            this.statement.setString(2, bibliotecario.getDireccion());
            this.statement.setString(3, bibliotecario.getTelefono());
            this.statement.setString(4, bibliotecario.getEmail());
            this.statement.setString(5, bibliotecario.getContrasenha());
            this.statement.setString(6, bibliotecario.getTurno().name());
            this.statement.setDate(7, new java.sql.Date(bibliotecario.getFechaContrato().getTime()));
            this.statement.setInt(8, bibliotecario.getIdPersona());
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
    public Integer eliminar(BibliotecarioDTO bibliotecario) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, bibliotecario.getIdPersona());
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
