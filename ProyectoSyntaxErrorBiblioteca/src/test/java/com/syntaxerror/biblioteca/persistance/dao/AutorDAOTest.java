/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.CreadorDTO;
import com.syntaxerror.biblioteca.model.enums.TipoAutor;
import com.syntaxerror.biblioteca.persistance.dao.impl.CreadorDAOImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

/**
 * Test class for CreadorDAO implementation
 */
public class AutorDAOTest {

    private final CreadorDAO creadorDAO;

    public AutorDAOTest() {
        this.creadorDAO = new CreadorDAOImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        eliminarTodo();
        System.out.println("insertar");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);
        eliminarTodo();
    }

    private void insertarAutores(ArrayList<Integer> listaIdAutor) {
        CreadorDTO autor = new CreadorDTO();
        autor.setNombre("J.K.");
        autor.setPaterno("Rowling");
        autor.setMaterno("");
        autor.setSeudonimo("J.K. Rowling");
        autor.setTipo(TipoAutor.AUTOR);
        autor.setNacionalidad("Británica");
        autor.setActivo(true);

        Integer resultado = this.creadorDAO.insertar(autor);
        assertTrue(resultado != 0);
        listaIdAutor.add(resultado);
        
        autor = new CreadorDTO(); // Create new instance to avoid reusing same object
        autor.setNombre("George");
        autor.setPaterno("Orwell");
        autor.setMaterno("");
        autor.setSeudonimo("George Orwell");
        autor.setTipo(TipoAutor.AUTOR);
        autor.setNacionalidad("Británico");
        autor.setActivo(false);

        resultado = this.creadorDAO.insertar(autor);
        assertTrue(resultado != 0);
        listaIdAutor.add(resultado);
    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);
        
        CreadorDTO autor = this.creadorDAO.obtenerPorId(listaIdAutor.get(0));
        assertNotNull(autor);
        assertEquals(listaIdAutor.get(0), autor.getIdAutor());
        assertEquals("J.K.", autor.getNombre());
        assertEquals("Rowling", autor.getPaterno());
        assertEquals("J.K. Rowling", autor.getSeudonimo());
        assertEquals("Británica", autor.getNacionalidad());
        assertTrue(autor.getActivo());

        autor = this.creadorDAO.obtenerPorId(listaIdAutor.get(1));
        assertNotNull(autor);
        assertEquals(listaIdAutor.get(1), autor.getIdAutor());
        assertEquals("George", autor.getNombre());
        assertEquals("Orwell", autor.getPaterno());
        assertEquals("George Orwell", autor.getSeudonimo());
        assertEquals("Británico", autor.getNacionalidad());
        assertFalse(autor.getActivo());

        eliminarTodo();
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);

        ArrayList<CreadorDTO> listaAutores = this.creadorDAO.listarTodos();
        assertEquals(listaIdAutor.size(), listaAutores.size());
        for (Integer i = 0; i < listaIdAutor.size(); i++) {
            assertEquals(listaIdAutor.get(i), listaAutores.get(i).getIdAutor());
        }
        eliminarTodo();
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);

        ArrayList<CreadorDTO> listaAutores = this.creadorDAO.listarTodos();
        assertEquals(listaIdAutor.size(), listaAutores.size());
        
        for (Integer i = 0; i < listaIdAutor.size(); i++) {
            CreadorDTO autor = listaAutores.get(i);
            autor.setNombre("NuevoNombre" + i);
            autor.setPaterno("NuevoPaterno" + i);
            autor.setMaterno("NuevoMaterno" + i);
            autor.setSeudonimo("NuevoSeudonimo" + i);
            autor.setNacionalidad("NuevaNacionalidad" + i);
            autor.setActivo(!autor.getActivo());
            
            Integer resultado = this.creadorDAO.modificar(autor);
            assertNotEquals(0, resultado);
        }

        ArrayList<CreadorDTO> listaAutoresModificados = this.creadorDAO.listarTodos();
        assertEquals(listaAutores.size(), listaAutoresModificados.size());
        for (Integer i = 0; i < listaAutores.size(); i++) {
            assertEquals(listaAutores.get(i).getNombre(), listaAutoresModificados.get(i).getNombre());
            assertEquals(listaAutores.get(i).getPaterno(), listaAutoresModificados.get(i).getPaterno());
            assertEquals(listaAutores.get(i).getMaterno(), listaAutoresModificados.get(i).getMaterno());
            assertEquals(listaAutores.get(i).getSeudonimo(), listaAutoresModificados.get(i).getSeudonimo());
            assertEquals(listaAutores.get(i).getNacionalidad(), listaAutoresModificados.get(i).getNacionalidad());
            assertEquals(listaAutores.get(i).getActivo(), listaAutoresModificados.get(i).getActivo());
        }
        eliminarTodo();
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<CreadorDTO> listaAutores = this.creadorDAO.listarTodos();
        for (CreadorDTO autor : listaAutores) {
            Integer resultado = this.creadorDAO.eliminar(autor);
            assertNotEquals(0, resultado);
            CreadorDTO autorEliminado = this.creadorDAO.obtenerPorId(autor.getIdAutor());
            assertNull(autorEliminado);
        }
    }
}
