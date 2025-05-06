package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.persistance.dao.LectorDAO;
import com.syntaxerror.biblioteca.model.LectorDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.model.enums.TipoSancion;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LectorDAOImpl extends DAOImplBase implements LectorDAO {

    private LectorDTO lector;
    private LectorDTO dtol;
    //private static Integer idInc=1;

    public LectorDAOImpl() {
        super("BIB_LECTOR");
        this.retornarLlavePrimaria = true;
        this.lector = null;
        dtol = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_LECTOR", true, true));
        this.listaColumnas.add(new Columna("NIVEL", false, false));
        this.listaColumnas.add(new Columna("PERSONA_IDPERSONA", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.lector.getNivel().name());
        this.statement.setInt(2, this.lector.getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.lector.getNivel().name());
        this.statement.setInt(2, this.lector.getIdPersona());
        this.statement.setInt(3, this.lector.getIdLector());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.lector.getIdLector());
    }

//    @Override
//    protected void instanciarObjetoDelResultSet() throws SQLException {
//        this.lector = new LectorDTO();
//        this.lector.setIdLector(this.resultSet.getInt("ID_LECTOR"));
//        this.lector.setNivel(NivelDeIngles.valueOf(this.resultSet.getString("NIVEL")));
//        this.lector.setIdPersona(this.resultSet.getInt("PERSONA_IDPERSONA"));
//
//    }

    @Override
    public Integer insertar(LectorDTO lector) {
        this.lector = lector;

        // Insertar primero en persona
        Integer idPersona = null;
        try {
            idPersona = insertarPersona(
                      
                    lector.getNombre(),
                    lector.getDireccion(),
                    lector.getTelefono(),
                    lector.getCorreo(),
                    lector.getContrasenha(),
                    lector.getSede().getIdSede()
            );
        } catch (SQLException ex) {
            Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (idPersona == null) {
            return null;
        }
        lector.setIdPersona(idPersona);
        
        // Luego insertar en BIB_LECTOR
        return super.insertar();
    }

    @Override
    public Integer modificar(LectorDTO lector) {
        this.lector = lector;

        // Modificar primero en persona
        boolean personaOk = false;
        try {
            personaOk = modificarPersona(
                    lector.getIdPersona(),
                    lector.getNombre(),
                    lector.getDireccion(),
                    lector.getTelefono(),
                    lector.getCorreo(),
                    lector.getContrasenha(),
                    lector.getSede().getIdSede()
            );
        } catch (SQLException ex) {
            Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!personaOk) {
            return null;
        }

        // Luego modificar en BIB_LECTOR
        return super.modificar();
    }

    @Override
    public Integer eliminar(Integer idLector) {
        // Primero borrar de LECTOR
        this.lector = new LectorDTO();
        this.lector.setIdLector(idLector);
        Integer result = super.eliminar();
        if (result > 0) {
            try {
                // Luego borrar de PERSONA
                eliminarPersonaByLector(idLector);
            } catch (SQLException ex) {
                Logger.getLogger(LectorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private LectorDTO mapearLectorDesdeResultSet(ResultSet rs) throws SQLException {
        LectorDTO dto = new LectorDTO();
        dto.setIdLector(rs.getInt("ID_LECTOR"));
        dto.setNivel(NivelDeIngles.valueOf(rs.getString("NIVEL")));
        dto.setIdPersona(rs.getInt("ID_PERSONA"));
        dto.setNombre(rs.getString("NOMBRE_COMPLETO"));
        dto.setDireccion(rs.getString("DIRECCION"));
        dto.setTelefono(rs.getString("TELEFONO"));
        dto.setCorreo(rs.getString("CORREO"));
        dto.setContrasenha(rs.getString("CONTRASENHA"));

        SedeDTO sede = new SedeDTO();
        sede.setIdSede(rs.getInt("SEDE_IDSEDE"));
        dto.setSede(sede);

        return dto;
    }

    @Override
    public LectorDTO obtenerPorId(Integer idLector) {
        String sql = "SELECT l.ID_LECTOR, l.NIVEL, p.ID_PERSONA, p.NOMBRE_COMPLETO, p.DIRECCION, p.TELEFONO, p.CORREO, p.CONTRASENHA, p.SEDE_IDSEDE "
                + "FROM BIB_LECTOR l JOIN BIB_PERSONA p ON l.PERSONA_IDPERSONA = p.ID_PERSONA "
                + "WHERE l.ID_LECTOR = ?";
        LectorDTO dto = null;
        try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idLector);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = mapearLectorDesdeResultSet(rs);
//                    dto = new LectorDTO();
//                    dto.setIdLector(rs.getInt("ID_LECTOR"));
//                    dto.setNivel(NivelDeIngles.valueOf(rs.getString("NIVEL")));
//                    dto.setIdPersona(rs.getInt("ID_PERSONA"));
//                    dto.setNombre(rs.getString("NOMBRE_COMPLETO"));
//                    dto.setDireccion(rs.getString("DIRECCION"));
//                    dto.setTelefono(rs.getString("TELEFONO"));
//                    dto.setCorreo(rs.getString("CORREO"));
//                    dto.setContrasenha(rs.getString("CONTRASENHA"));
//                    SedeDTO sede = new SedeDTO();
//                    sede.setIdSede(rs.getInt("SEDE_IDSEDE"));
//                    dto.setSede(sede);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public ArrayList<LectorDTO> listarTodos() {
        String sql = "SELECT l.ID_LECTOR, l.NIVEL, p.ID_PERSONA, p.NOMBRE_COMPLETO, p.DIRECCION, p.TELEFONO, p.CORREO, p.CONTRASENHA, p.SEDE_IDSEDE "
                + "FROM BIB_LECTOR l JOIN BIB_PERSONA p ON l.PERSONA_IDPERSONA = p.ID_PERSONA";
        ArrayList<LectorDTO> lista = new ArrayList<>();
        try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LectorDTO dto = mapearLectorDesdeResultSet(rs);
//                LectorDTO dto = new LectorDTO();
//                dto.setIdLector(rs.getInt("ID_LECTOR"));
//                dto.setNivel(NivelDeIngles.valueOf(rs.getString("NIVEL")));
//                dto.setIdPersona(rs.getInt("ID_PERSONA"));
//                dto.setNombre(rs.getString("NOMBRE_COMPLETO"));
//                dto.setDireccion(rs.getString("DIRECCION"));
//                dto.setTelefono(rs.getString("TELEFONO"));
//                dto.setCorreo(rs.getString("CORREO"));
//                dto.setContrasenha(rs.getString("CONTRASENHA"));
//
//                SedeDTO sede = new SedeDTO();
//                sede.setIdSede(rs.getInt("SEDE_IDSEDE")); // ← corregido aquí
//                dto.setSede(sede); // ← corregido aquí

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

//    @Override
//    public LectorDTO obtenerPorId(Integer idLector) {
//        String sql = "SELECT l.ID_LECTOR, l.NIVEL, p.ID_PERSONA, p.NOMBRE_COMPLETO, p.DIRECCION, p.TELEFONO, p.CORREO, p.CONTRASENHA, p.SEDE_IDSEDE "
//                + "FROM BIB_LECTOR l JOIN BIB_PERSONA p ON l.PERSONA_IDPERSONA = p.ID_PERSONA "
//                + "WHERE l.ID_LECTOR = ?";
//        LectorDTO dto = null;
//        try (Connection c = DBManager.getInstance().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//            ps.setInt(1, idLector);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    dto = new LectorDTO();
//                    dto.setIdLector(rs.getInt("ID_LECTOR"));
//                    dto.setNivel(NivelDeIngles.valueOf(rs.getString("NIVEL")));
//                    dto.setIdPersona(rs.getInt("ID_PERSONA"));
//                    dto.setNombre(rs.getString("NOMBRE_COMPLETO"));
//                    dto.setDireccion(rs.getString("DIRECCION"));
//                    dto.setTelefono(rs.getString("TELEFONO"));
//                    dto.setCorreo(rs.getString("CORREO"));
//                    dto.setContrasenha(rs.getString("CONTRASENHA"));
//                    SedeDTO sede = new SedeDTO();
//                    sede.setIdSede(this.resultSet.getInt("SEDE_IDSEDE"));
//                    this.lector.setSede(sede);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dto;
//    }
//    private Connection conexion;
//
//    public LectorDAOImpl(Connection conexion) {
//        this.conexion = conexion;
//    }
//
//    @Override
//    public Integer insertar(LectorDTO lector) {
//        String sqlPersona = "INSERT INTO BIB_PERSONA (NOMBRE, DIRECCION, TELEFONO, CORREO, CONTRASENHA, SEDE_IDSEDE) "
//                + "VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement statementPersona = conexion.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
//            statementPersona.setString(1, lector.getNombre());
//            statementPersona.setString(2, lector.getDireccion());
//            statementPersona.setString(3, lector.getTelefono());
//            statementPersona.setString(4, lector.getCorreo());
//            statementPersona.setString(5, lector.getContrasenha());
//            statementPersona.setInt(6, lector.getSede().getIdSede());
//
//            // Ejecutar inserción en Persona
//            int filasAfectadas = statementPersona.executeUpdate();
//            if (filasAfectadas == 0) {
//                return null;
//            }
//
//            // Recuperar el ID_PERSONA generado
//            ResultSet keys = statementPersona.getGeneratedKeys();
//            if (keys.next()) {
//                lector.setIdPersona(keys.getInt(1));
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        // Ahora insertar en BIB_LECTOR sin asignar ID_LECTOR manualmente
//        String sqlLector = "INSERT INTO BIB_LECTOR (PERSONA_IDPERSONA, NIVEL) VALUES (?, ?)";
//        try (PreparedStatement statementLector = conexion.prepareStatement(sqlLector, Statement.RETURN_GENERATED_KEYS)) {
//            statementLector.setString(1, lector.getNivel().name());
//            statementLector.setInt(2, lector.getIdPersona());
//
//            int filasAfectadasLector = statementLector.executeUpdate();
//            if (filasAfectadasLector > 0) {
//                ResultSet keysLector = statementLector.getGeneratedKeys();
//                if (keysLector.next()) {
//                    return keysLector.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public Integer modificar(LectorDTO lector) {
//        String sql = "UPDATE BIB_LECTOR SET NIVEL = ? WHERE ID_LECTOR = ?";
//        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
//            statement.setString(1, lector.getNivel().name());
//            statement.setInt(2, lector.getIdLector());
//            return statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Integer eliminar(Integer idLector) {
//        String sql = "DELETE FROM BIB_LECTOR WHERE ID_LECTOR = ?";
//        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
//            statement.setInt(1, idLector);
//            return statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public LectorDTO obtenerPorId(Integer idLector) {
//        String sql = "SELECT P.*, L.ID_LECTOR, L.NIVEL FROM BIB_PERSONA P "
//                + "JOIN BIB_LECTOR L ON P.ID_PERSONA = L.PERSONA_IDPERSONA "
//                + "WHERE L.ID_LECTOR = ?";
//
//        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
//            statement.setInt(1, idLector);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                LectorDTO lector = new LectorDTO();
//                lector.setIdPersona(resultSet.getInt("ID_PERSONA"));
//                lector.setNombre(resultSet.getString("NOMBRE_COMPLETO"));
//                lector.setDireccion(resultSet.getString("DIRECCION"));
//                lector.setTelefono(resultSet.getString("TELEFONO"));
//                lector.setCorreo(resultSet.getString("CORREO"));
//                lector.setContrasenha(resultSet.getString("CONTRASENHA"));
//                SedeDTO sede = new SedeDTO();
//                sede.setIdSede(resultSet.getInt("SEDE_IDSEDE"));
//                lector.setSede(sede);
//                lector.setIdLector(resultSet.getInt("ID_LECTOR"));
//                lector.setNivel(NivelDeIngles.valueOf(resultSet.getString("NIVEL")));
//                return lector;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public ArrayList<LectorDTO> listarTodos() {
//        ArrayList<LectorDTO> lista = new ArrayList<>();
//        String sql = "SELECT P.*, L.ID_LECTOR, L.NIVEL FROM BIB_PERSONA P "
//                + "JOIN BIB_LECTOR L ON P.ID_PERSONA = L.PERSONA_IDPERSONA";
//
//        try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                LectorDTO lector = new LectorDTO();
//                lector.setIdPersona(resultSet.getInt("ID_PERSONA"));
//                lector.setNombre(resultSet.getString("NOMBRE_COMPLETO"));
//                lector.setDireccion(resultSet.getString("DIRECCION"));
//                lector.setTelefono(resultSet.getString("TELEFONO"));
//                lector.setCorreo(resultSet.getString("CORREO"));
//                lector.setContrasenha(resultSet.getString("CONTRASENHA"))s;
//                SedeDTO sede = new SedeDTO();
//                sede.setIdSede(resultSet.getInt("SEDE_IDSEDE"));
//                lector.setSede(sede);
//                lector.setIdLector(resultSet.getInt("ID_LECTOR"));
//                lector.setNivel(NivelDeIngles.valueOf(resultSet.getString("NIVEL")));
//                lista.add(lector);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
}
