package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.EditorialDTO;
import java.util.List;
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
public class EditorialBOTest {

    private EditorialBO editorialBO;

    public EditorialBOTest() {
        this.editorialBO = new EditorialBO();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testInsertarEditorial() throws BusinessException {
        testEliminarTodo();
        int id = this.editorialBO.insertar("Planeta", "https://planeta.com", "España");
        assertTrue(id > 0);

        this.editorialBO.insertar("Editorial sin web", null, "Mexico");
    }

    @Test
    public void testListarTodos() {
        List<EditorialDTO> lista = editorialBO.listarTodos();

        assertNotNull(lista);

        // Imprimir
        for (EditorialDTO editorial : lista) {
            System.out.println("ID: " + editorial.getIdEditorial());
            System.out.println("Nombre: " + editorial.getNombre());
            System.out.println("Sitio web: " + editorial.getSitioWeb());
            System.out.println("País: " + editorial.getPais());
            System.out.println("----------------------");
        }
    }

    @Test
    public void testEliminarTodo() throws BusinessException {
        List<EditorialDTO> lista = editorialBO.listarTodos();

        for (EditorialDTO editorial : lista) {
            int resultado = editorialBO.eliminar(editorial.getIdEditorial());
            assertTrue(resultado > 0, "No se pudo eliminar la editorial con ID: " + editorial.getIdEditorial());
        }

        System.out.println("Todos los registros han sido eliminados correctamente.");
    }

    @Test
    public void testModificarEditorial() throws BusinessException {
        // Insertamos una editorial inicial
        int id = editorialBO.insertar("Editorial original", "https://original.com", "Chile");
        assertTrue(id > 0, "La inserción debe devolver un ID válido");

        // Modificamos los datos de esa editorial
        int resultado = editorialBO.modificar(id, "Editorial modificada", "https://modificada.com", "Argentina");
        assertTrue(resultado > 0, "La modificación debe devolver un valor mayor a 0");

        // Verificamos los cambios
        EditorialDTO editorial = editorialBO.obtenerPorId(id);//De pasada se prueba el obtenerId
        assertNotNull(editorial, "La editorial debe existir tras la modificación");
        assertEquals("Editorial modificada", editorial.getNombre());
        assertEquals("https://modificada.com", editorial.getSitioWeb());
        assertEquals("Argentina", editorial.getPais());
    }

    @Test
    public void testEliminarEditorial() throws BusinessException {
        // Primero insertamos una editorial
        int id = editorialBO.insertar("Editorial a eliminar", "https://elim.com", "Perú");
        assertTrue(id > 0, "La inserción debe devolver un ID válido");

        // Ahora eliminamos la editorial
        int resultado = editorialBO.eliminar(id);
        assertTrue(resultado > 0, "La eliminación debe devolver un valor mayor a 0");

        // Verificamos que ya no existe
        EditorialDTO editorial = editorialBO.obtenerPorId(id);
        assertNull(editorial, "La editorial eliminada no debe existir");
    }

}
