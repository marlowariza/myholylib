package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

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
//
    @Test
    @Order(1)
    public void testInsertar() {
        eliminarTodo();
        System.out.println("insertar");
        ArrayList<Integer> listaIdSede = new ArrayList<>();
        insertarSedes(listaIdSede);
        eliminarTodo();
    }

    private void insertarSedes(ArrayList<Integer> listaIdSede) {
        SedeDTO sede = new SedeDTO();
        sede.setNombre("Sede Central");
        sede.setDireccion("Av. Principal 123");
        sede.setDistrito("Distrito Central");
        sede.setTelefonoContacto("987654321");
        sede.setCorreoContacto("sede.central@email.com");
        sede.setActiva(true);

        Integer resultado = this.sedeDAO.insertar(sede);
        assertTrue(resultado != 0);
        listaIdSede.add(resultado);

        sede.setNombre("Sede PUCP");
        sede.setDireccion("Av. Universitaria 456");
        sede.setDistrito("San Miguel");
        sede.setTelefonoContacto("123456789");
        sede.setCorreoContacto("sede.pucp@email.com");
        sede.setActiva(true);
        resultado = this.sedeDAO.insertar(sede);
        assertTrue(resultado != 0);
        listaIdSede.add(resultado);

    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaIdSede = new ArrayList<>();
        insertarSedes(listaIdSede);
        SedeDTO sede = this.sedeDAO.obtenerPorId(listaIdSede.get(0));
        assertEquals(sede.getIdSede(), listaIdSede.get(0));

        sede = this.sedeDAO.obtenerPorId(listaIdSede.get(1));
        assertEquals(sede.getIdSede(), listaIdSede.get(1));

//        sede = this.sedeDAO.obtenerPorId(listaIdSede.get(2));
//        assertEquals(sede.getIdSede(), listaIdSede.get(2));
        eliminarTodo();
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaIdSede = new ArrayList<>();
        insertarSedes(listaIdSede);

        ArrayList<SedeDTO> listaSedes = this.sedeDAO.listarTodos();
        assertEquals(listaIdSede.size(), listaSedes.size());
        for (Integer i = 0; i < listaIdSede.size(); i++) {
            assertEquals(listaIdSede.get(i), listaSedes.get(i).getIdSede());
        }
        eliminarTodo();
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        ArrayList<Integer> listaIdSede = new ArrayList<>();
        insertarSedes(listaIdSede);

        ArrayList<SedeDTO> listaSedes = this.sedeDAO.listarTodos();
        assertEquals(listaIdSede.size(), listaSedes.size());
        for (Integer i = 0; i < listaIdSede.size(); i++) {
            listaSedes.get(i).setNombre("NuevoNombre" + i.toString());
            listaSedes.get(i).setDireccion("NuevaDireccion" + i.toString());
            listaSedes.get(i).setDistrito("NuevoDistrito" + i.toString());
            listaSedes.get(i).setTelefonoContacto("NuevoTelefono" + i.toString());
            listaSedes.get(i).setCorreoContacto("NuevoCorreo" + i.toString());
            listaSedes.get(i).setActiva(!listaSedes.get(i).getActiva());
            this.sedeDAO.modificar(listaSedes.get(i));

        }

        ArrayList<SedeDTO> listaSedesModificados = this.sedeDAO.listarTodos();
        assertEquals(listaSedes.size(), listaSedesModificados.size());
        for (Integer i = 0; i < listaSedes.size(); i++) {
            assertEquals(listaSedes.get(i).getNombre(), listaSedesModificados.get(i).getNombre());
            assertEquals(listaSedes.get(i).getDireccion(), listaSedesModificados.get(i).getDireccion());
            assertEquals(listaSedes.get(i).getDistrito(), listaSedesModificados.get(i).getDistrito());
            assertEquals(listaSedes.get(i).getTelefonoContacto(), listaSedesModificados.get(i).getTelefonoContacto());
            assertEquals(listaSedes.get(i).getCorreoContacto(), listaSedesModificados.get(i).getCorreoContacto());
            assertEquals(listaSedes.get(i).getActiva(), listaSedesModificados.get(i).getActiva());
        }


        eliminarTodo();
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        ArrayList<Integer> listaIdSede = new ArrayList<>();
        insertarSedes(listaIdSede);
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<SedeDTO> listaSedes = this.sedeDAO.listarTodos();
        for (Integer i = 0; i < listaSedes.size(); i++) {
            Integer resultado = this.sedeDAO.eliminar(listaSedes.get(i));
            assertNotEquals(0, resultado);
            SedeDTO almacen = this.sedeDAO.obtenerPorId(listaSedes.get(i).getIdSede());
            assertNull(almacen);
        }
    }

//        @Test
//    public void testInsertar_v1() {
//        System.out.println("insertar");
//        ArrayList<Integer> listaSedeId = new ArrayList<>(); // Lista para almacenar los IDs de las sedes insertadas
//        insertarSedes_v1(listaSedeId); // Método para insertar sedes
//        verificarInserciones_v1();
//    }
//
//    private void insertarSedes_v1(ArrayList<Integer> listaSedeId) {
//        SedeDTO sede = new SedeDTO(); // Crear un objeto SedeDTO
//
//        // Insertar la primera sede
//        //sede.setIdSede(1);
//        sede.setNombre("Sede Central");
//        sede.setDireccion("Av. Principal 123");
//        sede.setDistrito("Distrito Central");
//        sede.setTelefono_contacto("987654321");
//        sede.setCorreo_contacto("sede.central@email.com");
//        sede.setActiva(true);
//        Integer resultado = this.sedeDAO.insertar(sede); // Llamada al método insertar del DAO
//        listaSedeId.add(resultado); // Agregar el ID de la sede a la lista
//
//        // Insertar la segunda sede
//        //sede.setIdSede(2);
//        sede.setNombre("Sede PUCP");
//        sede.setDireccion("Av. Universitaria 456");
//        sede.setDistrito("San Miguel");
//        sede.setTelefono_contacto("123456789");
//        sede.setCorreo_contacto("sede.pucp@email.com");
//        sede.setActiva(true);
//        resultado = this.sedeDAO.insertar(sede);
//        listaSedeId.add(resultado);
//
//    }
//
//    public void verificarInserciones_v1() {
//        String sql = "SELECT * FROM BIB_SEDE WHERE ID_SEDE IN (1, 2)";
//        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                //System.out.println("IdSede: " + rs.getInt("ID_SEDE"));
//                System.out.println("Nombre: " + rs.getString("NOMBRE"));
//                System.out.println("Direccion: " + rs.getString("DIRECCION"));
//                System.out.println("Distrito: " + rs.getString("DISTRITO"));
//                System.out.println("TelefonoContacto: " + rs.getString("TELEFONO_CONTACTO"));
//                System.out.println("CorreoContacto: " + rs.getString("CORREO_CONTACTO"));
//                System.out.println("Activa: " + rs.getInt("ACTIVA"));
//                System.out.println("-----");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        eliminarTodo();
//    }
}
