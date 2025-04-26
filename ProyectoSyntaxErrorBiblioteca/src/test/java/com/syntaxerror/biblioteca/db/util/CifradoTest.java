/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.db.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alulab14
 */
public class CifradoTest {
    
    public CifradoTest() {
    }

    @Test
    public void testDescifrarMD5() {
        System.out.println("descifrarMD5");
        String texto = "TqGBwWiNFI01e0raRf/9cw==";
        String resultado = Cifrado.descifrarMD5(texto);
        String resultado_esperado = "UnoDosTres45";
        assertEquals(resultado, resultado_esperado);
    }
    
}
