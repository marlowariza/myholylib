

package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.AutorDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.AutorDAOImpl;
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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutorDAOTest {
    private AutorDAO autorDAO;
    
    public AutorDAOTest() {
        this.autorDAO = new AutorDAOImpl(); // Instancia el objeto correctamente
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("insertar");
        ArrayList<Integer> listaAutorId = new ArrayList<>();
        insertarAutores(listaAutorId);
        verificarInserciones();
    }

    private void insertarAutores(ArrayList<Integer> listaAutorId) {
        // Insertar el primer autor
        AutorDTO autor = new AutorDTO();
        
        autor.setIdAutor(1);
        autor.setNombre("Gabriel García Márquez");
        autor.setNacionalidad("Colombiano");
        autor.setActivo(true);
        autor.setCantidadObras(15);
        Integer resultado = this.autorDAO.insertar(autor);
        listaAutorId.add(resultado);

        // Insertar el segundo autor
        autor.setIdAutor(2);
        autor.setNombre("Mario Vargas Llosa");
        autor.setNacionalidad("Peruano");
        autor.setActivo(true);
        autor.setCantidadObras(20);
        resultado = this.autorDAO.insertar(autor);
        listaAutorId.add(resultado);
    }

    private void verificarInserciones() {
        String sql = "SELECT * FROM Autor WHERE IdAutor IN (1, 2)";
        try (Connection conn = DBManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
         
            while (rs.next()) {
                System.out.println("IdAutor: " + rs.getInt("IdAutor"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
                System.out.println("Activo: " + rs.getBoolean("Activo"));
                System.out.println("CantidadObras: " + rs.getInt("CantidadObras"));
                System.out.println("-----");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    @Order(2)
    public void testModificar() {
        System.out.println("modificar");

        // Modificar autor con Id 1 (Gabriel García Márquez)
        AutorDTO autor1 = new AutorDTO();
        autor1.setIdAutor(1);
        autor1.setNombre("Gabo Márquez");
        autor1.setNacionalidad("Colombiano");
        autor1.setActivo(false);
        autor1.setCantidadObras(16);
        Integer filasAfectadas1 = this.autorDAO.modificar(autor1);

        // Modificar autor con Id 2 (Mario Vargas Llosa)
        AutorDTO autor2 = new AutorDTO();
        autor2.setIdAutor(2);
        autor2.setNombre("M. Vargas Llosa");
        autor2.setNacionalidad("Peruano-Español");
        autor2.setActivo(false);
        autor2.setCantidadObras(25);
        Integer filasAfectadas2 = this.autorDAO.modificar(autor2);

        assertEquals(1, filasAfectadas1, "Debe modificarse 1 fila (autor 1)");
        assertEquals(1, filasAfectadas2, "Debe modificarse 1 fila (autor 2)");

        verificarModificaciones();
    }
    
    private void verificarModificaciones() {
        String sql = "SELECT * FROM Autor WHERE IdAutor IN (1, 2)";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                    System.out.println("IdAutor: " + rs.getInt("IdAutor"));
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
                    System.out.println("Activo: " + rs.getBoolean("Activo"));
                    System.out.println("CantidadObras: " + rs.getInt("CantidadObras"));
                    System.out.println("-----");
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
            fail("Error al verificar las modificaciones en la BD");
        }
    
    }
    
    @Test
    @Order(3)
    public void testEliminar() {
        System.out.println("eliminar");
        eliminarAutores();
        verificarEliminaciones();
    }
    
    private void eliminarAutores() {
        AutorDTO autor1 = new AutorDTO();
        autor1.setIdAutor(1); // Solo necesitas el ID para eliminar

        AutorDTO autor2 = new AutorDTO();
        autor2.setIdAutor(2);

        Integer resultado1 = autorDAO.eliminar(autor1);
        Integer resultado2 = autorDAO.eliminar(autor2);

        assertEquals(1, resultado1, "Debe eliminarse 1 fila (autor 1)");
        assertEquals(1, resultado2, "Debe eliminarse 1 fila (autor 2)");
    }
    
    private void verificarEliminaciones() {
        String sql = "SELECT * FROM Autor WHERE IdAutor IN (1, 2)";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.next()) {
                System.out.println("Verificación: los autores fueron eliminados correctamente.");
            } else {
                do {
                    System.out.println("Autor no eliminado:");
                    System.out.println("IdAutor: " + rs.getInt("IdAutor"));
                    System.out.println("Nombre: " + rs.getString("Nombre"));
                    System.out.println("-----");
                } while (rs.next());
                fail("Uno o más autores aún existen en la base de datos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            fail("Error al verificar eliminación de autores");
        }
    }
}






//package com.syntaxerror.biblioteca.persistance.dao;
//
//import com.syntaxerror.biblioteca.db.DBManager;
//import com.syntaxerror.biblioteca.model.AutorDTO;
//import com.syntaxerror.biblioteca.persistance.dao.impl.AutorDAOImp;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AutorDAOTest {
//    private AutorDAO autorDAO;
//    
//    public AutorDAOTest() {
//        this.autorDAO = new AutorDAOImp(); // Instancia el objeto correctamente
//    }
//
//
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        ArrayList<Integer> listaAutorId = new ArrayList<>();
//        insertarAutores(listaAutorId);
//        verificarInserciones();
//    }
//
//    private void insertarAutores(ArrayList<Integer> listaAutorId) {
//        // Insertar el primer autor
//        AutorDTO autor = new AutorDTO();
//        
//        autor.setIdAutor(1);
//        autor.setNombre("Gabriel García Márquez");
//        autor.setNacionalidad("Colombiano");
//        autor.setActivo(true);
//        autor.setCantidadObras(15);
//        Integer resultado = this.autorDAO.insertar(autor);
//        listaAutorId.add(resultado);
//
//        // Insertar el segundo autor
//        autor.setIdAutor(2);
//        autor.setNombre("Mario Vargas Llosa");
//        autor.setNacionalidad("Peruano");
//        autor.setActivo(true);
//        autor.setCantidadObras(20);
//        resultado = this.autorDAO.insertar(autor);
//        listaAutorId.add(resultado);
//    }
//
//    private void verificarInserciones() {
//        String sql = "SELECT * FROM Autor WHERE IdAutor IN (1, 2)";
//        try (Connection conn = DBManager.getInstance().getConnection();
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery()) {
//         
//            while (rs.next()) {
//                System.out.println("IdAutor: " + rs.getInt("IdAutor"));
//                System.out.println("Nombre: " + rs.getString("Nombre"));
//                System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
//                System.out.println("Activo: " + rs.getBoolean("Activo"));
//                System.out.println("CantidadObras: " + rs.getInt("CantidadObras"));
//                System.out.println("-----");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//
//        // Modificar autor con Id 1 (Gabriel García Márquez)
//        AutorDTO autor1 = new AutorDTO();
//        autor1.setIdAutor(1);
//        autor1.setNombre("Gabo Márquez");
//        autor1.setNacionalidad("Colombiano");
//        autor1.setActivo(false);
//        autor1.setCantidadObras(16);
//        Integer filasAfectadas1 = this.autorDAO.modificar(autor1);
//
//        // Modificar autor con Id 2 (Mario Vargas Llosa)
//        AutorDTO autor2 = new AutorDTO();
//        autor2.setIdAutor(2);
//        autor2.setNombre("M. Vargas Llosa");
//        autor2.setNacionalidad("Peruano-Español");
//        autor2.setActivo(false);
//        autor2.setCantidadObras(25);
//        Integer filasAfectadas2 = this.autorDAO.modificar(autor2);
//
//        assertEquals(1, filasAfectadas1, "Debe modificarse 1 fila (autor 1)");
//        assertEquals(1, filasAfectadas2, "Debe modificarse 1 fila (autor 2)");
//
//        verificarModificaciones();
//    }
//    
//    private void verificarModificaciones() {
//    String sql = "SELECT * FROM Autor WHERE IdAutor IN (1, 2)";
//    try (Connection conn = DBManager.getInstance().getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql);
//         ResultSet rs = stmt.executeQuery()) {
//
//        while (rs.next()) {
//                System.out.println("IdAutor: " + rs.getInt("IdAutor"));
//                System.out.println("Nombre: " + rs.getString("Nombre"));
//                System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
//                System.out.println("Activo: " + rs.getBoolean("Activo"));
//                System.out.println("CantidadObras: " + rs.getInt("CantidadObras"));
//                System.out.println("-----");
//            }
//
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        fail("Error al verificar las modificaciones en la BD");
//    }
//}
//}



























//    public AutorDAOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of insertar method, of class AutorDAO.
//     */
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        AutorDTO autor = null;
//        AutorDAO instance = new AutorDAOImpl();
//        Integer expResult = null;
//        Integer result = instance.insertar(autor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerPorId method, of class AutorDAO.
//     */
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId");
//        Integer id = null;
//        AutorDAO instance = new AutorDAOImpl();
//        AutorDTO expResult = null;
//        AutorDTO result = instance.obtenerPorId(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificar method, of class AutorDAO.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        AutorDTO autor = null;
//        AutorDAO instance = new AutorDAOImpl();
//        Integer expResult = null;
//        Integer result = instance.modificar(autor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminar method, of class AutorDAO.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        AutorDTO autor = null;
//        AutorDAO instance = new AutorDAOImpl();
//        Integer expResult = null;
//        Integer result = instance.eliminar(autor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarTodos method, of class AutorDAO.
//     */
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos");
//        AutorDAO instance = new AutorDAOImpl();
//        ArrayList<AutorDTO> expResult = null;
//        ArrayList<AutorDTO> result = instance.listarTodos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class AutorDAOImpl implements AutorDAO {
//
//        public Integer insertar(AutorDTO autor) {
//            return null;
//        }
//
//        public AutorDTO obtenerPorId(Integer id) {
//            return null;
//        }
//
//        public Integer modificar(AutorDTO autor) {
//            return null;
//        }
//
//        public Integer eliminar(AutorDTO autor) {
//            return null;
//        }
//
//        public ArrayList<AutorDTO> listarTodos() {
//            return null;
//        }
//    }
//    
//}
