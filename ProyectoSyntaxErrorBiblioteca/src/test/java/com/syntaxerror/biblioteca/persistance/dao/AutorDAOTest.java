///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package com.syntaxerror.biblioteca.persistance.dao;
//
//import com.syntaxerror.biblioteca.model.AutorDTO;
//import java.util.ArrayList;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author josue
// */
//public class AutorDAOTest {
//    
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
//        Integer autorId = null;
//        AutorDAO instance = new AutorDAOImpl();
//        AutorDTO expResult = null;
//        AutorDTO result = instance.obtenerPorId(autorId);
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
//    public class AutorDAOImpl implements AutorDAO {
//
//        public Integer insertar(AutorDTO autor) {
//            return null;
//        }
//
//        public AutorDTO obtenerPorId(Integer autorId) {
//            return null;
//        }
//
//        public ArrayList<AutorDTO> listarTodos() {
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
//    }
//    
//}
