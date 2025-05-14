package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.ReporteGeneralDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.syntaxerror.biblioteca.persistance.dao.impl.ReporteGeneralDAOImpl;
import java.util.ArrayList;

public class ReporteGeneralDAOTest {

    private ReporteGeneralDAO reporteGeneralDAO;

    public ReporteGeneralDAOTest() {
        this.reporteGeneralDAO = new ReporteGeneralDAOImpl();
    }

    @Test
    public void testListarPorPeriodo() {
        System.out.println("Test: listarPorPeriodo");

        // Paso 1: insertar datos de prueba
        this.reporteGeneralDAO.insertarDatosDePrueba();

        // Paso 2: generar el reporte (ej. mayo 2025)
        this.reporteGeneralDAO.generarReporteGeneral(2025, 5);

        // Paso 3: consultar el reporte
        ArrayList<ReporteGeneralDTO> listado = this.reporteGeneralDAO.listarPorPeriodo(2025, 5, null, null);

// Paso 4: imprimir resultados en consola
        if (!listado.isEmpty()) {
            ReporteGeneralDTO primerReporte = listado.get(0);
            System.out.println("Año " + primerReporte.getAnio() + " Mes " + primerReporte.getMes() + ":");

            for (ReporteGeneralDTO r : listado) {
                String nombreCompleto = r.getPersona().getNombre() + " "
                        + r.getPersona().getPaterno() + " "
                        + r.getPersona().getMaterno();

                System.out.println("Persona: " + nombreCompleto + ", Préstamo ID: "
                        + r.getPrestamo().getIdPrestamo() + ", Fecha préstamo: "
                        + r.getPrestamo().getFechaPrestamo());
            }
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("No se encontraron datos para el período especificado.");
        }

        // Paso 5: validaciones
        assertNotNull(listado);
        assertTrue(listado.size() > 0);

        // Paso 6: eliminar datos de prueba
        this.reporteGeneralDAO.eliminarDatosDePrueba();
    }
}
