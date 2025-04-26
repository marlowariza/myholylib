/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.db;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alulab14
 */
public class DBManagerTest {
    /**
     * Test of getInstance method, of class DBManager.
     */
    
    public DBManagerTest() {
        //DBManager dbManager = new DBManager();
    }
    
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DBManager dbManager = DBManager.getInstance();
        assertNotNull(dbManager);
        System.out.println(dbManager);
        DBManager dbManagerClone = DBManager.getInstance();
        System.out.println(dbManager);
        assertEquals(dbManager, dbManagerClone);
    }

    /**
     * Test of getConnection method, of class DBManager.
     */
    @org.junit.jupiter.api.Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBManager dbManager = DBManager.getInstance();
        Connection conexion = dbManager.getConnection();
        assertNotNull(conexion);
        System.out.println(conexion);
        conexion = dbManager.getConnection();
        assertNotNull(conexion);
        System.out.println(conexion);
    }
    
}
