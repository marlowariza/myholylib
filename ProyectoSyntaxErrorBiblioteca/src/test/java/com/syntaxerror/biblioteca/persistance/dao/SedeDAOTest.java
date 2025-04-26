
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.SedeDTO;
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

/**
 *
 * @author Fabian
 */
public class SedeDAOTest {

    private SedeDAO sedeDAO;

    public SedeDAOTest() {
        this.sedeDAO = new SedeDAOImpl(); // Instancia el objeto correctamente
    }

    /**
     * Test of insertar method, of class SedeDAO.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        ArrayList<Integer> listaSedeId = new ArrayList<>(); // Lista para almacenar los IDs de las sedes insertadas
        insertarSedes(listaSedeId); // Método para insertar sedes
        verificarInserciones();
    }

    private void insertarSedes(ArrayList<Integer> listaSedeId) {
        SedeDTO sede = new SedeDTO(); // Crear un objeto SedeDTO

        // Insertar la primera sede
        sede.setIdSede(1);
        sede.setNombre("Sede Central");
        sede.setDireccion("Av. Principal 123");
        sede.setDistrito("Distrito Central");
        sede.setTelefono_contacto("987654321");
        sede.setCorreo_contacto("sede.central@email.com");
        Integer resultado = this.sedeDAO.insertar(sede); // Llamada al método insertar del DAO
        listaSedeId.add(resultado); // Agregar el ID de la sede a la lista

        // Insertar la segunda sede
        sede.setIdSede(2);
        sede.setNombre("Sede PUCP");
        sede.setDireccion("Av. Universitaria 456");
        sede.setDistrito("San Miguel");
        sede.setTelefono_contacto("123456789");
        sede.setCorreo_contacto("sede.pucp@email.com");
        resultado = this.sedeDAO.insertar(sede);
        listaSedeId.add(resultado);

    }
    
    public void verificarInserciones() {
    String sql = "SELECT * FROM Sede WHERE IdSede IN (1, 2)";
    try (Connection conn = DBManager.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
         
        while (rs.next()) {
            System.out.println("IdSede: " + rs.getInt("IdSede"));
            System.out.println("Nombre: " + rs.getString("Nombre"));
            System.out.println("Direccion: " + rs.getString("Direccion"));
            System.out.println("Distrito: " + rs.getString("Distrito"));
            System.out.println("TelefonoContacto: " + rs.getString("TelefonoContacto"));
            System.out.println("CorreoContacto: " + rs.getString("CorreoContacto"));
            System.out.println("-----");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    /**
     * Test of obtenerPorId method, of class SedeDAO.
     */
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId");
//        Integer idSede = null;
//        SedeDAO instance = new SedeDAOImpl();
//        SedeDTO expResult = null;
//        SedeDTO result = instance.obtenerPorId(idSede);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarTodos method, of class SedeDAO.
//     */
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos");
//        SedeDAO instance = new SedeDAOImpl();
//        ArrayList<SedeDTO> expResult = null;
//        ArrayList<SedeDTO> result = instance.listarTodos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificar method, of class SedeDAO.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        SedeDTO sede = null;
//        SedeDAO instance = new SedeDAOImpl();
//        Integer expResult = null;
//        Integer result = instance.modificar(sede);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminar method, of class SedeDAO.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Integer idSede = null;
//        SedeDAO instance = new SedeDAOImpl();
//        Integer expResult = null;
//        Integer result = instance.eliminar(idSede);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class SedeDAOImpl implements SedeDAO {
//
//        public Integer insertar(SedeDTO sede) {
//            return null;
//        }
//
//        public SedeDTO obtenerPorId(Integer idSede) {
//            return null;
//        }
//
//        public ArrayList<SedeDTO> listarTodos() {
//            return null;
//        }
//
//        public Integer modificar(SedeDTO sede) {
//            return null;
//        }
//
//        public Integer eliminar(Integer idSede) {
//            return null;
//        }
//    }
}
