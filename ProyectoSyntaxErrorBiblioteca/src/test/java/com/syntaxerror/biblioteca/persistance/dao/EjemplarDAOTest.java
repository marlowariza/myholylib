/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.EjemplarDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.EjemplarDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

/**
 *
 * @author Fabian
 */
public class EjemplarDAOTest {

    private EjemplarDAO ejemplarDAO;
    private SedeDAO sedeDAO;
    private MaterialDAO materialDAO;
    private EditorialDAO editorialDAO;

    public EjemplarDAOTest() {
        this.ejemplarDAO = new EjemplarDAOImpl(); // Instancia el objeto correctamente
        this.sedeDAO = new SedeDAOImpl(); // Instancia el objeto correctamente
        this.materialDAO = new MaterialDAOImpl(); // Instancia el objeto correctamente
        this.editorialDAO = new EditorialDAOImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        eliminarTodo();
        System.out.println("insertar");
        ArrayList<Integer> listaIdEjemplar = new ArrayList<>();
        insertarEjemplares(listaIdEjemplar);
        eliminarTodo();
    }

    private void insertarEjemplares(ArrayList<Integer> listaIdEjemplar) {
        EjemplarDTO ejemplar = new EjemplarDTO();

        SedeDTO sede = obtenerSedeFK();
        MaterialDTO material = obtenerMaterialFK();
        ejemplar.setFechaAdquisicion(Date.valueOf("2023-05-01"));
        ejemplar.setDisponible(true);
        ejemplar.setUbicacion("Pasillo-3");
        ejemplar.setSede(sede);
        ejemplar.setMaterial(material);

        Integer resultado = this.ejemplarDAO.insertar(ejemplar);
        assertTrue(resultado != 0);
        listaIdEjemplar.add(resultado);

    }

    private SedeDTO obtenerSedeFK() {
        SedeDTO sede = new SedeDTO();
        sede.setNombre("Sede Test");
        sede.setDireccion("Dirección Test");
        sede.setDistrito("Distrito Test");
        sede.setTelefonoContacto("123456789");
        sede.setCorreoContacto("test@test.com");
        sede.setActiva(true);

        // Insertar la sede y obtener su ID
        Integer idSede = this.sedeDAO.insertar(sede);
        sede.setIdSede(idSede);
        return sede;
    }

    private EditorialDTO obtenerEditorialFK() {
        EditorialDTO editorial = new EditorialDTO();
        editorial.setNombre("Editorial Test");
        editorial.setPais("País Test");

        // Insertar la editorial y obtener su ID
        Integer idEditorial = this.editorialDAO.insertar(editorial);
        editorial.setIdEditorial(idEditorial);
        return editorial;
    }

    private MaterialDTO obtenerMaterialFK() {
        MaterialDTO material = new MaterialDTO();
        material.setTitulo("Material Test");
        material.setEdicion("1ra Edición");
        material.setNivel(NivelDeIngles.BASICO);
        material.setAnioPublicacion(2024);

        // Obtener y establecer la editorial
        EditorialDTO editorial = obtenerEditorialFK();
        material.setEditorial(editorial);

        // Insertar el material y obtener su ID
        Integer idMaterial = this.materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);
        return material;
    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaIdEjemplar = new ArrayList<>();
        insertarEjemplares(listaIdEjemplar);
        EjemplarDTO ejemplar = this.ejemplarDAO.obtenerPorId(listaIdEjemplar.get(0));
        assertEquals(ejemplar.getIdEjemplar(), listaIdEjemplar.get(0));
        eliminarTodo();
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaIdEjemplar = new ArrayList<>();
        insertarEjemplares(listaIdEjemplar);

        ArrayList<EjemplarDTO> listaEjemplares = this.ejemplarDAO.listarTodos();
        assertEquals(listaIdEjemplar.size(), listaEjemplares.size());
        for (Integer i = 0; i < listaIdEjemplar.size(); i++) {
            assertEquals(listaIdEjemplar.get(i), listaEjemplares.get(i).getIdEjemplar());
        }
        eliminarTodo();
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        ArrayList<Integer> listaIdEjemplar = new ArrayList<>();
        insertarEjemplares(listaIdEjemplar);

        ArrayList<EjemplarDTO> listaEjemplares = this.ejemplarDAO.listarTodos();
        assertEquals(listaIdEjemplar.size(), listaEjemplares.size());
        for (Integer i = 0; i < listaIdEjemplar.size(); i++) {
            listaEjemplares.get(i).setFechaAdquisicion(Date.valueOf("2025-05-01"));
            listaEjemplares.get(i).setDisponible(!listaEjemplares.get(i).getDisponible());
            listaEjemplares.get(i).setUbicacion("Mas a la derecha" + i.toString());
            this.ejemplarDAO.modificar(listaEjemplares.get(i));

        }

        ArrayList<EjemplarDTO> listaEjemplaresModificados = this.ejemplarDAO.listarTodos();
        assertEquals(listaEjemplares.size(), listaEjemplaresModificados.size());
        for (Integer i = 0; i < listaEjemplares.size(); i++) {
            assertEquals(listaEjemplares.get(i).getFechaAdquisicion(),
                    listaEjemplaresModificados.get(i).getFechaAdquisicion());
            assertEquals(listaEjemplares.get(i).getDisponible(), listaEjemplaresModificados.get(i).getDisponible());
            assertEquals(listaEjemplares.get(i).getUbicacion(), listaEjemplaresModificados.get(i).getUbicacion());
        }

        eliminarTodo();
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        ArrayList<Integer> listaIdEjemplar = new ArrayList<>();
        insertarEjemplares(listaIdEjemplar);
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<EjemplarDTO> listaEjemplares = this.ejemplarDAO.listarTodos();
        for (Integer i = 0; i < listaEjemplares.size(); i++) {
            Integer resultado = this.ejemplarDAO.eliminar(listaEjemplares.get(i));
            assertNotEquals(0, resultado);
            EjemplarDTO almacen = this.ejemplarDAO.obtenerPorId(listaEjemplares.get(i).getIdEjemplar());
            assertNull(almacen);
        }
    }

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        eliminarTodo();
    }

    @AfterEach
    public void tearDown() {
        // Limpiar la base de datos después de cada prueba
        eliminarTodo();
    }
}
