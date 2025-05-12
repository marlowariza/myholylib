package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

/**
 * Test class for MaterialDAO implementation
 * Prueba todas las operaciones CRUD sobre la tabla BIB_MATERIAL
 * Los IDs son autoincrementales, manejados por la base de datos
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialDAOTest {

    private final MaterialDAO materialDAO;
    private final EditorialDAO editorialDAO;
    private EditorialDTO editorial;

    public MaterialDAOTest() {
        this.materialDAO = new MaterialDAOImpl();
        this.editorialDAO = new EditorialDAOImpl();
    }

    @BeforeEach
    public void setUp() {
        limpiarBaseDeDatos();
        // Create a test editorial for foreign key relationship
        editorial = new EditorialDTO();
        editorial.setNombre("Editorial Test");
        editorial.setPais("País Test");
        editorial.setSitioWeb("http://test.com");
        Integer idEditorial = editorialDAO.insertar(editorial);
        editorial.setIdEditorial(idEditorial);
    }

    @AfterEach
    public void tearDown() {
        limpiarBaseDeDatos();
    }

    /**
     * Método auxiliar para limpiar la base de datos
     * Elimina todos los materiales y editoriales existentes
     */
    private void limpiarBaseDeDatos() {
        // Clean up all materials first (due to foreign key constraints)
        ArrayList<MaterialDTO> materiales = materialDAO.listarTodos();
        for (MaterialDTO material : materiales) {
            materialDAO.eliminar(material);
        }
        
        // Then clean up all editorials
        ArrayList<EditorialDTO> editoriales = editorialDAO.listarTodos();
        for (EditorialDTO editorial : editoriales) {
            editorialDAO.eliminar(editorial);
        }
        
        // Verify cleanup
        assertTrue(materialDAO.listarTodos().isEmpty(), 
                  "La base de datos de materiales debería estar vacía después de la limpieza");
    }

    private MaterialDTO crearMaterialPrueba(String titulo) {
        MaterialDTO material = new MaterialDTO();
        material.setTitulo(titulo);
        material.setEdicion("Primera");
        material.setNivel(NivelDeIngles.INTERMEDIO);
        material.setAnioPublicacion(2024);
        material.setEditorial(editorial);
        return material;
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("insertar");
        
        MaterialDTO material1 = crearMaterialPrueba("Material Test 1");
        MaterialDTO material2 = crearMaterialPrueba("Material Test 2");
        
        // Test inserción del primer material
        Integer id1 = materialDAO.insertar(material1);
        assertNotNull(id1, "El ID generado no debería ser null");
        assertTrue(id1 > 0, "El ID generado debería ser mayor que 0");
        
        // Test inserción del segundo material
        Integer id2 = materialDAO.insertar(material2);
        assertNotNull(id2, "El ID generado no debería ser null");
        assertTrue(id2 > id1, "El segundo ID debería ser mayor que el primero (autoincremental)");
        
        // Verificar que se insertaron correctamente
        ArrayList<MaterialDTO> materiales = materialDAO.listarTodos();
        assertEquals(2, materiales.size(), "Deberían haber exactamente dos materiales en la base de datos");
        
        // Verificar que los materiales se pueden recuperar por sus IDs
        MaterialDTO recuperado1 = materialDAO.obtenerPorId(id1);
        MaterialDTO recuperado2 = materialDAO.obtenerPorId(id2);
        assertNotNull(recuperado1, "El primer material debería existir");
        assertNotNull(recuperado2, "El segundo material debería existir");
        assertEquals("Material Test 1", recuperado1.getTitulo());
        assertEquals("Material Test 2", recuperado2.getTitulo());
    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        
        // Insertar material de prueba
        MaterialDTO materialOriginal = crearMaterialPrueba("Material Test");
        Integer id = materialDAO.insertar(materialOriginal);
        assertNotNull(id, "El ID generado no debería ser null");
        materialOriginal.setIdMaterial(id);
        
        // Obtener el material por ID
        MaterialDTO materialObtenido = materialDAO.obtenerPorId(id);
        
        // Verificar que todos los campos coinciden
        assertNotNull(materialObtenido, "El material obtenido no debería ser null");
        assertEquals(materialOriginal.getIdMaterial(), materialObtenido.getIdMaterial(), "Los IDs deberían coincidir");
        assertEquals(materialOriginal.getTitulo(), materialObtenido.getTitulo(), "Los títulos deberían coincidir");
        assertEquals(materialOriginal.getEdicion(), materialObtenido.getEdicion(), "Las ediciones deberían coincidir");
        assertEquals(materialOriginal.getNivel(), materialObtenido.getNivel(), "Los niveles deberían coincidir");
        assertEquals(materialOriginal.getAnioPublicacion(), materialObtenido.getAnioPublicacion(), "Los años deberían coincidir");
        assertEquals(materialOriginal.getEditorial().getIdEditorial(), 
                   materialObtenido.getEditorial().getIdEditorial(), "Los IDs de editorial deberían coincidir");
        
        // Verificar que un ID inexistente retorna null
        assertNull(materialDAO.obtenerPorId(99999), "Un ID inexistente debería retornar null");
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        
        // Verificar lista vacía inicial
        assertTrue(materialDAO.listarTodos().isEmpty(), "La lista debería estar vacía inicialmente");
        
        // Insertar varios materiales
        ArrayList<MaterialDTO> materialesOriginales = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            MaterialDTO material = crearMaterialPrueba("Material Test " + i);
            Integer id = materialDAO.insertar(material);
            assertNotNull(id, "El ID generado no debería ser null");
            material.setIdMaterial(id);
            materialesOriginales.add(material);
        }
        
        // Obtener lista de materiales
        ArrayList<MaterialDTO> materialesObtenidos = materialDAO.listarTodos();
        
        // Verificar cantidad
        assertEquals(materialesOriginales.size(), materialesObtenidos.size(), 
                    "El número de materiales obtenidos debería coincidir con los insertados");
        
        // Verificar que cada material existe en la lista y sus IDs son únicos
        for (MaterialDTO materialOriginal : materialesOriginales) {
            boolean encontrado = materialesObtenidos.stream()
                .anyMatch(m -> m.getIdMaterial().equals(materialOriginal.getIdMaterial()));
            assertTrue(encontrado, "No se encontró el material con ID: " + materialOriginal.getIdMaterial());
        }
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        
        // Insertar material de prueba
        MaterialDTO material = crearMaterialPrueba("Material Original");
        Integer id = materialDAO.insertar(material);
        assertNotNull(id, "El ID generado no debería ser null");
        material.setIdMaterial(id);
        
        // Modificar campos
        material.setTitulo("Material Modificado");
        material.setEdicion("Segunda");
        material.setNivel(NivelDeIngles.AVANZADO);
        material.setAnioPublicacion(2025);
        
        // Realizar modificación
        Integer resultado = materialDAO.modificar(material);
        assertNotEquals(0, resultado, "La modificación debería ser exitosa");
        
        // Verificar cambios
        MaterialDTO materialModificado = materialDAO.obtenerPorId(id);
        assertNotNull(materialModificado, "El material modificado debería existir");
        assertEquals(material.getTitulo(), materialModificado.getTitulo(), "El título debería estar actualizado");
        assertEquals(material.getEdicion(), materialModificado.getEdicion(), "La edición debería estar actualizada");
        assertEquals(material.getNivel(), materialModificado.getNivel(), "El nivel debería estar actualizado");
        assertEquals(material.getAnioPublicacion(), materialModificado.getAnioPublicacion(), "El año debería estar actualizado");
        assertEquals(id, materialModificado.getIdMaterial(), "El ID no debería cambiar");
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        
        // Insertar material de prueba
        MaterialDTO material = crearMaterialPrueba("Material a Eliminar");
        Integer id = materialDAO.insertar(material);
        assertNotNull(id, "El ID generado no debería ser null");
        material.setIdMaterial(id);
        
        // Verificar que existe
        assertNotNull(materialDAO.obtenerPorId(id), "El material debería existir antes de eliminarlo");
        
        // Eliminar
        Integer resultado = materialDAO.eliminar(material);
        assertNotEquals(0, resultado, "La eliminación debería ser exitosa");
        
        // Verificar que ya no existe
        assertNull(materialDAO.obtenerPorId(id), "El material no debería existir después de eliminarlo");
    }
} 