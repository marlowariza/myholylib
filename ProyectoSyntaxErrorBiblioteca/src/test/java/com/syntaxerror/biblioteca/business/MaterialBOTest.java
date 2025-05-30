package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaterialBOTest {

    private static MaterialBO materialBO;
    private static EditorialBO editorialBO;
    private static int idEditorial;

    public MaterialBOTest() {

    }

    @BeforeAll
    public static void setUpClass() throws BusinessException {
        materialBO = new MaterialBO();
        editorialBO = new EditorialBO();
        //Para insertar una sola editorial
        idEditorial = editorialBO.insertar("Editorial de prueba", "https://prueba.com", "Perú");
    }

    @AfterAll
    public static void tearDownClass() throws BusinessException {
        editorialBO.eliminar(idEditorial);
    }

    @BeforeEach
    public void setUp() throws BusinessException {
        testEliminarTodo();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testInsertarMaterial() throws BusinessException {
        testEliminarTodo();
        // Insertamos un material
        int id = materialBO.insertar("Java para principiantes", "1ª edición", NivelDeIngles.BASICO, 2022, idEditorial); // 1 es el idEditorial
        assertTrue(id > 0, "El ID de la inserción debe ser mayor que 0");

        id = materialBO.insertar("C# Avanzado", "2 edicion", NivelDeIngles.AVANZADO, 2021, idEditorial);
        assertTrue(id > 0, "El ID de la inserción debe ser mayor que 0");
    }

    @Test
    public void testListarTodos() {
        List<MaterialDTO> lista = materialBO.listarTodos();

        assertNotNull(lista, "La lista de materiales no debe ser nula");

        // Imprimir
        for (MaterialDTO material : lista) {
            System.out.println("ID: " + material.getIdMaterial());
            System.out.println("Título: " + material.getTitulo());
            System.out.println("Edición: " + material.getEdicion());
            System.out.println("Nivel: " + material.getNivel());
            System.out.println("Año de publicación: " + material.getAnioPublicacion());
            System.out.println("Editorial ID: " + material.getEditorial().getIdEditorial());
            System.out.println("----------------------");
        }
    }

    @Test
    public void testEliminarTodo() throws BusinessException {
        List<MaterialDTO> lista = materialBO.listarTodos();

        for (MaterialDTO material : lista) {
            int resultado = materialBO.eliminar(material.getIdMaterial());
            assertTrue(resultado > 0, "No se pudo eliminar el material con ID: " + material.getIdMaterial());
        }

        System.out.println("Todos los registros han sido eliminados correctamente.");
    }

    @Test
    public void testModificarMaterial() throws BusinessException {
        // Insertamos un material inicial
        int id = materialBO.insertar("Material original", "1ª edición", NivelDeIngles.INTERMEDIO, 2020, idEditorial);
        assertTrue(id > 0, "La inserción debe devolver un ID válido");

        // Modificamos los datos de ese material
        int resultado = materialBO.modificar(id, "Material modificado", "2ª edición", NivelDeIngles.AVANZADO, 2021, idEditorial);
        assertTrue(resultado > 0, "La modificación debe devolver un valor mayor a 0");

        // Verificamos los cambios
        MaterialDTO material = materialBO.obtenerPorId(id);
        assertNotNull(material, "El material debe existir tras la modificación");
        assertEquals("Material modificado", material.getTitulo());
        assertEquals("2ª edición", material.getEdicion());
        assertEquals(NivelDeIngles.AVANZADO, material.getNivel());
        assertEquals(2021, material.getAnioPublicacion());
    }

    @Test
    public void testEliminarMaterial() throws BusinessException {
        // Primero insertamos un material
        int id = materialBO.insertar("Material a eliminar", "1ª edición", NivelDeIngles.BASICO, 2022, idEditorial);
        assertTrue(id > 0, "La inserción debe devolver un ID válido");

        // Ahora eliminamos el material
        int resultado = materialBO.eliminar(id);
        assertTrue(resultado > 0, "La eliminación debe devolver un valor mayor a 0");

        // Verificamos que ya no existe
    }
}
