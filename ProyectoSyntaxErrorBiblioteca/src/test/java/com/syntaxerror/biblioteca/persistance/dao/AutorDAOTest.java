/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.AutorDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.AutorDAOImpl;
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
public class AutorDAOTest {

    private AutorDAO autorDAO;

    public AutorDAOTest() {
        this.autorDAO = new AutorDAOImpl(); // Instancia el objeto correctamente
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
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);
        eliminarTodo();
    }

    private void insertarAutores(ArrayList<Integer> listaIdAutor) {
        AutorDTO autor = new AutorDTO();
        autor.setNombre("J.K. Rowling");
        autor.setNacionalidad("Británica");
        autor.setActivo(true);
        autor.setCantidadObras(15);
        

        Integer resultado = this.autorDAO.insertar(autor);
        assertTrue(resultado != 0);
        listaIdAutor.add(resultado);
        
        autor.setNombre("George Orwell");
        autor.setNacionalidad("Británico");
        autor.setActivo(false);
        autor.setCantidadObras(10);
        

        resultado = this.autorDAO.insertar(autor);
        assertTrue(resultado != 0);
        listaIdAutor.add(resultado);
    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);
        AutorDTO autor = this.autorDAO.obtenerPorId(listaIdAutor.get(0));
        assertEquals(autor.getIdAutor(), listaIdAutor.get(0));

        autor = this.autorDAO.obtenerPorId(listaIdAutor.get(1));
        assertEquals(autor.getIdAutor(), listaIdAutor.get(1));

        eliminarTodo();
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaIdAutor = new ArrayList<>();
        insertarAutores(listaIdAutor);

        ArrayList<AutorDTO> listaAutores = this.autorDAO.listarTodos();
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

        ArrayList<AutorDTO> listaAutores = this.autorDAO.listarTodos();
        assertEquals(listaIdAutor.size(), listaAutores.size());
        for (Integer i = 0; i < listaIdAutor.size(); i++) {
            listaAutores.get(i).setNombre("NuevoNombre" + i.toString());
            listaAutores.get(i).setNacionalidad("NuevoSitioWeb" + i.toString());
            listaAutores.get(i).setActivo(!listaAutores.get(i).getActivo());
            listaAutores.get(i).setCantidadObras(listaAutores.get(i).getCantidadObras()-3);
            this.autorDAO.modificar(listaAutores.get(i));

        }

        ArrayList<AutorDTO> listaAutoresModificados = this.autorDAO.listarTodos();
        assertEquals(listaAutores.size(), listaAutoresModificados.size());
        for (Integer i = 0; i < listaAutores.size(); i++) {
            assertEquals(listaAutores.get(i).getNombre(), listaAutoresModificados.get(i).getNombre());
            assertEquals(listaAutores.get(i).getNacionalidad(), listaAutoresModificados.get(i).getNacionalidad());
            assertEquals(listaAutores.get(i).getActivo(), listaAutoresModificados.get(i).getActivo());
            assertEquals(listaAutores.get(i).getCantidadObras(), listaAutoresModificados.get(i).getCantidadObras());
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
        ArrayList<AutorDTO> listaAutores = this.autorDAO.listarTodos();
        for (Integer i = 0; i < listaAutores.size(); i++) {
            Integer resultado = this.autorDAO.eliminar(listaAutores.get(i));
            assertNotEquals(0, resultado);
            AutorDTO almacen = this.autorDAO.obtenerPorId(listaAutores.get(i).getIdAutor());
            assertNull(almacen);
        }
    }
}
