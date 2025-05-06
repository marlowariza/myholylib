package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.LectorDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.LectorDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

public class LectorDAOTest {

    private LectorDAO lectorDAO;
    private SedeDAO sedeDAO;

    public LectorDAOTest() {
        this.lectorDAO = new LectorDAOImpl();
        this.sedeDAO = new SedeDAOImpl(); // Instancia el objeto correctamente
    }

    /**
     * 1) Insertamos dos lectores distintos, y luego los borramos.
     */
    @Test
    @Order(1)
    public void testInsertar() throws SQLException {
        eliminarTodo();
        ArrayList<Integer> ids = new ArrayList<>();
        insertarLectores(ids);
        // Al final limpiamos
        eliminarTodo();
    }

    private void insertarLectores(ArrayList<Integer> ids) throws SQLException {
        LectorDTO lector = new LectorDTO();
        lector.setNombre("Juan Pérez");
        lector.setDireccion("Av. Siempre Viva 123");
        lector.setTelefono("987654321");
        lector.setCorreo("juan.perez@example.com");
        lector.setContrasenha("secreto");

        SedeDTO sede = obtenerSedeFK();   // constructor por defecto
        lector.setSede(sede);

        lector.setNivel(NivelDeIngles.BASICO);

        Integer id1 = lectorDAO.insertar(lector);
        assertNotNull(id1);
        assertTrue(id1 > 0);
        ids.add(id1);

        // Mostrar datos de BIB_PERSONA
        String sqlPersona = "SELECT * FROM BIB_PERSONA WHERE ID_PERSONA = ?";
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlPersona)) {

            ps.setInt(1, lector.getIdPersona());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("---- DATOS DE BIB_PERSONA ----");
                    System.out.println("ID_PERSONA: " + rs.getInt("ID_PERSONA"));
                    System.out.println("NOMBRE_COMPLETO: " + rs.getString("NOMBRE_COMPLETO"));
                    System.out.println("DIRECCION: " + rs.getString("DIRECCION"));
                    System.out.println("TELEFONO: " + rs.getString("TELEFONO"));
                    System.out.println("CORREO: " + rs.getString("CORREO"));
                } else {
                    System.out.println("No se encontró el lector en BIB_PERSONA");
                }
            }
        }

        // Mostrar datos de BIB_LECTOR
        String sqlLector = "SELECT * FROM BIB_LECTOR WHERE ID_LECTOR = ?";
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlLector)) {

            ps.setInt(1, id1);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("---- DATOS DE BIB_LECTOR ----");
                    System.out.println("ID_LECTOR: " + rs.getInt("ID_LECTOR"));
                    System.out.println("NIVEL: " + rs.getString("NIVEL"));
                    System.out.println("PERSONA_IDPERSONA: " + rs.getInt("PERSONA_IDPERSONA"));
                } else {
                    System.out.println("No se encontró el lector en BIB_LECTOR");
                }
            }
        }

        // Segundo lector
        lector = new LectorDTO();
        lector.setNombre("María García");
        lector.setDireccion("Calle Falsa 456");
        lector.setTelefono("912345678");
        lector.setCorreo("maria.garcia@example.com");
        lector.setContrasenha("password");
        lector.setSede(sede);
        lector.setNivel(NivelDeIngles.INTERMEDIO);

        Integer id2 = lectorDAO.insertar(lector);
        assertNotNull(id2);
        assertTrue(id2 > 0);
        ids.add(id2);
    }

    private SedeDTO obtenerSedeFK() {
        SedeDTO sede = new SedeDTO();
        sede.setNombre("Sede Test");
        sede.setDireccion("Dirección Test");
        sede.setDistrito("Distrito Test");
        sede.setTelefonoContacto("123456789");
        sede.setCorreoContacto("test@test.com");
        sede.setActiva(true);

        // Insertar la sede y obtener su ID
        Integer idSede = this.sedeDAO.insertar(sede);
        sede.setIdSede(idSede);
        return sede;
    }

    /**
     * 2) Recuperar por ID debe devolver exactamente ese lector.
     */
    @Test
    @Order(2)
    public void testObtenerPorId() throws SQLException {
        eliminarTodo();
        ArrayList<Integer> ids = new ArrayList<>();
        insertarLectores(ids);

        LectorDTO l1 = lectorDAO.obtenerPorId(ids.get(0));
        assertNotNull(l1);
        assertEquals(ids.get(0), l1.getIdLector());

        LectorDTO l2 = lectorDAO.obtenerPorId(ids.get(1));
        assertNotNull(l2);
        assertEquals(ids.get(1), l2.getIdLector());

        eliminarTodo();
    }

    /**
     * 3) listarTodos debe traer tantos lectores como insertamos, en orden de
     * inserción.
     */
    @Test
    @Order(3)
    public void testListarTodos() throws SQLException {
        eliminarTodo();
        ArrayList<Integer> ids = new ArrayList<>();
        insertarLectores(ids);

        ArrayList<LectorDTO> lista = lectorDAO.listarTodos();
        assertEquals(ids.size(), lista.size());
        for (int i = 0; i < ids.size(); i++) {
            assertEquals(ids.get(i), lista.get(i).getIdLector());
        }

        eliminarTodo();
    }

    /**
     * 4) Modificar debe cambiar los campos en BD.
     */
    @Test
    @Order(4)
    public void testModificar() throws SQLException {
        eliminarTodo();
        ArrayList<Integer> ids = new ArrayList<>();
        insertarLectores(ids);

        ArrayList<LectorDTO> lista = lectorDAO.listarTodos();
        assertEquals(ids.size(), lista.size());

        // Actualizo cada uno
        for (int i = 0; i < lista.size(); i++) {
            LectorDTO dto = lista.get(i);
            dto.setNombre("NombreModificado" + i);
            dto.setDireccion("DirModificada" + i);
            dto.setTelefono("999" + i);
            dto.setCorreo("modificado" + i + "@example.com");
            dto.setContrasenha("newPass" + i);
            dto.setNivel((i % 2 == 0) ? NivelDeIngles.AVANZADO : NivelDeIngles.BASICO);
            lectorDAO.modificar(dto);
        }

        // Releo y compruebo
        ArrayList<LectorDTO> mod = lectorDAO.listarTodos();
        assertEquals(lista.size(), mod.size());
        for (int i = 0; i < mod.size(); i++) {
            assertEquals(lista.get(i).getNombre(), mod.get(i).getNombre());
            assertEquals(lista.get(i).getDireccion(), mod.get(i).getDireccion());
            assertEquals(lista.get(i).getTelefono(), mod.get(i).getTelefono());
            assertEquals(lista.get(i).getCorreo(), mod.get(i).getCorreo());
            assertEquals(lista.get(i).getContrasenha(), mod.get(i).getContrasenha());
            assertEquals(lista.get(i).getNivel(), mod.get(i).getNivel());
        }

        eliminarTodo();
    }

    /**
     * 5) Eliminar debe borrar de LECTOR y de PERSONA, y obtenerPorId devolver
     * null.
     */
    @Test
    @Order(5)
    public void testEliminar() throws SQLException {
        eliminarTodo();
        ArrayList<Integer> ids = new ArrayList<>();
        insertarLectores(ids);
        // simplemente borramos todo
        eliminarTodo();
    }

    /**
     * Borra todos los lectores activos en BD, comprobando que ya no existan.
     */
    private void eliminarTodo() {
        ArrayList<LectorDTO> lista = lectorDAO.listarTodos();
        for (LectorDTO dto : lista) {
            Integer res = lectorDAO.eliminar(dto.getIdLector());
            assertNotNull(res);
            assertTrue(res > 0);
            // tras borrar, obtenerPorId debe ser null
            assertNull(lectorDAO.obtenerPorId(dto.getIdLector()));
        }
    }
}
