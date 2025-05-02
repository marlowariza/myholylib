/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.db.DBManager;
import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
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
public class EditorialDAOTest {

    private EditorialDAO editorialDAO;

    public EditorialDAOTest() {
        this.editorialDAO = new EditorialDAOImpl(); // Instancia el objeto correctamente
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
        ArrayList<Integer> listaIdEditorial = new ArrayList<>();
        insertarEditoriales(listaIdEditorial);
        eliminarTodo();
    }

    private void insertarEditoriales(ArrayList<Integer> listaIdEditorial) {
        EditorialDTO editorial = new EditorialDTO();
        editorial.setNombre("Editorial Planeta");
        editorial.setSitioWeb("https://www.planetadelibros.com");
        editorial.setPais("España");

        Integer resultado = this.editorialDAO.insertar(editorial);
        assertTrue(resultado != 0);
        listaIdEditorial.add(resultado);

        editorial.setNombre("Penguin Random House");
        editorial.setSitioWeb("https://www.penguinrandomhouse.com");
        editorial.setPais("Estados Unidos");
        resultado = this.editorialDAO.insertar(editorial);
        assertTrue(resultado != 0);
        listaIdEditorial.add(resultado);

    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaIdEditorial = new ArrayList<>();
        insertarEditoriales(listaIdEditorial);
        EditorialDTO editorial = this.editorialDAO.obtenerPorId(listaIdEditorial.get(0));
        assertEquals(editorial.getIdEditorial(), listaIdEditorial.get(0));

        editorial = this.editorialDAO.obtenerPorId(listaIdEditorial.get(1));
        assertEquals(editorial.getIdEditorial(), listaIdEditorial.get(1));

//        sede = this.sedeDAO.obtenerPorId(listaIdSede.get(2));
//        assertEquals(sede.getIdSede(), listaIdSede.get(2));
        eliminarTodo();
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaIdEditorial = new ArrayList<>();
        insertarEditoriales(listaIdEditorial);

        ArrayList<EditorialDTO> listaEditoriales = this.editorialDAO.listarTodos();
        assertEquals(listaIdEditorial.size(), listaEditoriales.size());
        for (Integer i = 0; i < listaIdEditorial.size(); i++) {
            assertEquals(listaIdEditorial.get(i), listaEditoriales.get(i).getIdEditorial());
        }
        eliminarTodo();
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        ArrayList<Integer> listaIdEditorial = new ArrayList<>();
        insertarEditoriales(listaIdEditorial);

        ArrayList<EditorialDTO> listaEditoriales = this.editorialDAO.listarTodos();
        assertEquals(listaIdEditorial.size(), listaEditoriales.size());
        for (Integer i = 0; i < listaIdEditorial.size(); i++) {
            listaEditoriales.get(i).setNombre("NuevoNombre" + i.toString());
            listaEditoriales.get(i).setSitioWeb("NuevoSitioWeb" + i.toString());
            listaEditoriales.get(i).setPais("NuevoPais" + i.toString());
            this.editorialDAO.modificar(listaEditoriales.get(i));

        }

        ArrayList<EditorialDTO> listaEditorialesModificadas = this.editorialDAO.listarTodos();
        assertEquals(listaEditoriales.size(), listaEditorialesModificadas.size());
        for (Integer i = 0; i < listaEditoriales.size(); i++) {
            assertEquals(listaEditoriales.get(i).getNombre(), listaEditorialesModificadas.get(i).getNombre());
            assertEquals(listaEditoriales.get(i).getSitioWeb(), listaEditorialesModificadas.get(i).getSitioWeb());
            assertEquals(listaEditoriales.get(i).getPais(), listaEditorialesModificadas.get(i).getPais());
        }


        eliminarTodo();
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        ArrayList<Integer> listaIdEditorial = new ArrayList<>();
        insertarEditoriales(listaIdEditorial);
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<EditorialDTO> listaEditoriales = this.editorialDAO.listarTodos();
        for (Integer i = 0; i < listaEditoriales.size(); i++) {
            Integer resultado = this.editorialDAO.eliminar(listaEditoriales.get(i));
            assertNotEquals(0, resultado);
            EditorialDTO almacen = this.editorialDAO.obtenerPorId(listaEditoriales.get(i).getIdEditorial());
            assertNull(almacen);
        }
    }
}
