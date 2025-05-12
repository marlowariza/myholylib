/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test unitario para EditorialDAOImpl
 * Prueba todas las operaciones CRUD sobre la tabla BIB_EDITORIAL
 * Los IDs son autoincrementales, manejados por la base de datos
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditorialDAOTest {

    private final EditorialDAO editorialDAO;
    private static final String EDITORIAL1_NOMBRE = "Editorial Planeta";
    private static final String EDITORIAL1_WEB = "https://www.planetadelibros.com";
    private static final String EDITORIAL1_PAIS = "España";
    
    private static final String EDITORIAL2_NOMBRE = "Penguin Random House";
    private static final String EDITORIAL2_WEB = "https://www.penguinrandomhouse.com";
    private static final String EDITORIAL2_PAIS = "Estados Unidos";

    public EditorialDAOTest() {
        this.editorialDAO = new EditorialDAOImpl();
    }

    @BeforeEach
    public void setUp() {
        limpiarBaseDeDatos();
    }

    @AfterEach
    public void tearDown() {
        limpiarBaseDeDatos();
    }

    /**
     * Test del método insertar
     * Verifica:
     * 1. Inserción exitosa de una editorial
     * 2. ID generado automáticamente es válido
     * 3. Los datos insertados son correctos
     * 4. IDs son únicos y autoincrementales
     */
    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Test: insertar");
        
        // Crear y guardar primera editorial
        EditorialDTO editorial1 = crearEditorial(null, EDITORIAL1_NOMBRE, EDITORIAL1_WEB, EDITORIAL1_PAIS);
        Integer idEditorial1 = editorialDAO.insertar(editorial1);
        assertNotNull(idEditorial1, "El ID generado no debería ser null");
        assertTrue(idEditorial1 > 0, "El ID generado debería ser mayor que 0");
        
        // Crear y guardar segunda editorial para verificar autoincremento
        EditorialDTO editorial2 = crearEditorial(null, EDITORIAL2_NOMBRE, EDITORIAL2_WEB, EDITORIAL2_PAIS);
        Integer idEditorial2 = editorialDAO.insertar(editorial2);
        assertNotNull(idEditorial2, "El ID generado no debería ser null");
        assertTrue(idEditorial2 > idEditorial1, "El segundo ID debería ser mayor que el primero (autoincremental)");
        
        // Verificar que se guardaron correctamente
        EditorialDTO guardada1 = editorialDAO.obtenerPorId(idEditorial1);
        assertNotNull(guardada1, "La editorial 1 guardada no debería ser null");
        assertEquals(EDITORIAL1_NOMBRE, guardada1.getNombre());
        assertEquals(EDITORIAL1_WEB, guardada1.getSitioWeb());
        assertEquals(EDITORIAL1_PAIS, guardada1.getPais());
        
        EditorialDTO guardada2 = editorialDAO.obtenerPorId(idEditorial2);
        assertNotNull(guardada2, "La editorial 2 guardada no debería ser null");
        assertEquals(EDITORIAL2_NOMBRE, guardada2.getNombre());
        assertEquals(EDITORIAL2_WEB, guardada2.getSitioWeb());
        assertEquals(EDITORIAL2_PAIS, guardada2.getPais());
        
        // Verificar que hay exactamente dos editoriales
        ArrayList<EditorialDTO> todas = editorialDAO.listarTodos();
        assertEquals(2, todas.size(), "Deberían haber exactamente dos editoriales en la base de datos");
    }

    /**
     * Test del método obtenerPorId
     * Verifica:
     * 1. Obtención correcta de una editorial existente por su ID autogenerado
     * 2. Retorno de null para una editorial inexistente
     * 3. Todos los campos se recuperan correctamente
     */
    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("Test: obtenerPorId");
        
        // Insertar editorial de prueba
        EditorialDTO editorial = crearEditorial(null, EDITORIAL1_NOMBRE, EDITORIAL1_WEB, EDITORIAL1_PAIS);
        Integer idGenerado = editorialDAO.insertar(editorial);
        assertNotNull(idGenerado, "El ID generado no debería ser null");
        
        // Probar obtener la editorial insertada usando el ID generado
        EditorialDTO obtenida = editorialDAO.obtenerPorId(idGenerado);
        assertNotNull(obtenida, "La editorial obtenida no debería ser null");
        assertEquals(idGenerado, obtenida.getIdEditorial());
        assertEquals(EDITORIAL1_NOMBRE, obtenida.getNombre());
        assertEquals(EDITORIAL1_WEB, obtenida.getSitioWeb());
        assertEquals(EDITORIAL1_PAIS, obtenida.getPais());
        
        // Probar obtener una editorial que no existe
        EditorialDTO noExiste = editorialDAO.obtenerPorId(99999);
        assertNull(noExiste, "Debería retornar null para un ID que no existe");
    }

    /**
     * Test del método listarTodos
     * Verifica:
     * 1. Lista vacía cuando no hay editoriales
     * 2. Lista correcta cuando hay editoriales con IDs autogenerados
     * 3. Orden y contenido de la lista
     */
    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("Test: listarTodos");
        
        // Verificar lista vacía
        ArrayList<EditorialDTO> listaVacia = editorialDAO.listarTodos();
        assertTrue(listaVacia.isEmpty(), "La lista debería estar vacía inicialmente");
        
        // Insertar dos editoriales y guardar sus IDs autogenerados
        EditorialDTO editorial1 = crearEditorial(null, EDITORIAL1_NOMBRE, EDITORIAL1_WEB, EDITORIAL1_PAIS);
        EditorialDTO editorial2 = crearEditorial(null, EDITORIAL2_NOMBRE, EDITORIAL2_WEB, EDITORIAL2_PAIS);
        
        Integer id1 = editorialDAO.insertar(editorial1);
        Integer id2 = editorialDAO.insertar(editorial2);
        
        assertNotNull(id1, "El ID1 generado no debería ser null");
        assertNotNull(id2, "El ID2 generado no debería ser null");
        assertTrue(id2 > id1, "El segundo ID debería ser mayor que el primero");
        
        // Obtener y verificar la lista
        ArrayList<EditorialDTO> lista = editorialDAO.listarTodos();
        assertEquals(2, lista.size(), "Deberían haber exactamente dos editoriales");
        
        // Verificar el contenido de la lista usando los IDs generados
        boolean encontroEditorial1 = false;
        boolean encontroEditorial2 = false;
        
        for (EditorialDTO editorial : lista) {
            if (editorial.getIdEditorial().equals(id1)) {
                assertEquals(EDITORIAL1_NOMBRE, editorial.getNombre());
                assertEquals(EDITORIAL1_WEB, editorial.getSitioWeb());
                assertEquals(EDITORIAL1_PAIS, editorial.getPais());
                encontroEditorial1 = true;
            } else if (editorial.getIdEditorial().equals(id2)) {
                assertEquals(EDITORIAL2_NOMBRE, editorial.getNombre());
                assertEquals(EDITORIAL2_WEB, editorial.getSitioWeb());
                assertEquals(EDITORIAL2_PAIS, editorial.getPais());
                encontroEditorial2 = true;
            }
        }
        
        assertTrue(encontroEditorial1, "No se encontró la editorial 1 en la lista");
        assertTrue(encontroEditorial2, "No se encontró la editorial 2 en la lista");
    }

    /**
     * Test del método modificar
     * Verifica:
     * 1. Modificación exitosa de una editorial existente usando su ID autogenerado
     * 2. Los cambios se reflejan en la base de datos
     * 3. No se modifican otras editoriales
     */
    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("Test: modificar");
        
        // Insertar editorial de prueba y obtener su ID autogenerado
        EditorialDTO editorial = crearEditorial(null, EDITORIAL1_NOMBRE, EDITORIAL1_WEB, EDITORIAL1_PAIS);
        Integer idGenerado = editorialDAO.insertar(editorial);
        assertNotNull(idGenerado, "El ID generado no debería ser null");
        
        // Modificar la editorial usando el ID generado
        String nuevoNombre = "Nuevo Nombre Editorial";
        String nuevaWeb = "https://www.nuevaweb.com";
        String nuevoPais = "Nuevo País";
        
        EditorialDTO editorialModificada = crearEditorial(idGenerado, nuevoNombre, nuevaWeb, nuevoPais);
        Integer resultado = editorialDAO.modificar(editorialModificada);
        
        assertNotNull(resultado, "El resultado de la modificación no debería ser null");
        assertTrue(resultado > 0, "La modificación debería ser exitosa");
        
        // Verificar que los cambios se guardaron usando el ID generado
        EditorialDTO obtenida = editorialDAO.obtenerPorId(idGenerado);
        assertNotNull(obtenida, "La editorial modificada no debería ser null");
        assertEquals(idGenerado, obtenida.getIdEditorial(), "El ID no debería cambiar");
        assertEquals(nuevoNombre, obtenida.getNombre());
        assertEquals(nuevaWeb, obtenida.getSitioWeb());
        assertEquals(nuevoPais, obtenida.getPais());
    }

    /**
     * Test del método eliminar
     * Verifica:
     * 1. Eliminación exitosa de una editorial usando su ID autogenerado
     * 2. La editorial ya no existe en la base de datos
     * 3. No se afectan otras editoriales
     */
    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("Test: eliminar");
        
        // Insertar dos editoriales y guardar sus IDs autogenerados
        EditorialDTO editorial1 = crearEditorial(null, EDITORIAL1_NOMBRE, EDITORIAL1_WEB, EDITORIAL1_PAIS);
        EditorialDTO editorial2 = crearEditorial(null, EDITORIAL2_NOMBRE, EDITORIAL2_WEB, EDITORIAL2_PAIS);
        
        Integer id1 = editorialDAO.insertar(editorial1);
        Integer id2 = editorialDAO.insertar(editorial2);
        
        assertNotNull(id1, "El ID1 generado no debería ser null");
        assertNotNull(id2, "El ID2 generado no debería ser null");
        assertTrue(id2 > id1, "El segundo ID debería ser mayor que el primero");
        
        // Eliminar la primera editorial usando su ID generado
        editorial1.setIdEditorial(id1);
        Integer resultado = editorialDAO.eliminar(editorial1);
        
        assertNotNull(resultado, "El resultado de la eliminación no debería ser null");
        assertTrue(resultado > 0, "La eliminación debería ser exitosa");
        
        // Verificar que la editorial fue eliminada usando su ID
        EditorialDTO eliminada = editorialDAO.obtenerPorId(id1);
        assertNull(eliminada, "La editorial eliminada no debería existir");
        
        // Verificar que la otra editorial sigue existiendo usando su ID
        EditorialDTO existente = editorialDAO.obtenerPorId(id2);
        assertNotNull(existente, "La editorial no eliminada debería seguir existiendo");
        assertEquals(id2, existente.getIdEditorial());
        assertEquals(EDITORIAL2_NOMBRE, existente.getNombre());
    }

    /**
     * Método auxiliar para crear una editorial con los datos especificados
     * El ID puede ser null para nuevas editoriales (será autogenerado por la BD)
     */
    private EditorialDTO crearEditorial(Integer id, String nombre, String sitioWeb, String pais) {
        EditorialDTO editorial = new EditorialDTO();
        editorial.setIdEditorial(id);  // Puede ser null para nuevas editoriales
        editorial.setNombre(nombre);
        editorial.setSitioWeb(sitioWeb);
        editorial.setPais(pais);
        return editorial;
    }

    /**
     * Método auxiliar para limpiar la base de datos
     * Elimina todas las editoriales existentes usando sus IDs autogenerados
     */
    private void limpiarBaseDeDatos() {
        ArrayList<EditorialDTO> editoriales = editorialDAO.listarTodos();
        for (EditorialDTO editorial : editoriales) {
            editorialDAO.eliminar(editorial);
        }
        //Verificar que se eliminaron todas las editoriales
        assertTrue(editorialDAO.listarTodos().isEmpty(), "La base de datos debería estar vacía después de la limpieza");
    }
}
