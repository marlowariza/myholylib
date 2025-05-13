package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.ReporteSedeDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.ReporteSedeDAOImpl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteSedeDAOTest {

    private ReporteSedeDAO reporteSedeDAO;

    public ReporteSedeDAOTest() {
        this.reporteSedeDAO = new ReporteSedeDAOImpl();
    }

    @Test
    public void testListarPorPeriodoYSede() {
        System.out.println("Test: listarPorPeriodoYSede");

        // Paso 1: insertar datos de prueba
        this.reporteSedeDAO.insertarDatosDePrueba();

        // Paso 2: generar el reporte (ej. mayo 2025)
        this.reporteSedeDAO.generarReportePorSede(2025, 5);

        // Paso 3: consultar reporte
        ArrayList<ReporteSedeDTO> listado = this.reporteSedeDAO.listarPorPeriodoYSede(2025, 5, null, null, null);

        // Paso 4: imprimir en consola
        //puede variar

          imprimirReporteSedeAgrupadoPorSede(listado);

        // Paso 5: validación lógica
        assertNotNull(listado);
        assertTrue(listado.size() > 0);

        //Paso 6: eliminar datos de prueba
        this.reporteSedeDAO.eliminarDatosDePrueba();

    }

    private void imprimirReporteSedeAgrupadoPorSede(List<ReporteSedeDTO> listado) {
        if (listado == null || listado.isEmpty()) {
            System.out.println("No se encontraron datos para el período especificado.");
            return;
        }

        ReporteSedeDTO primerReporte = listado.get(0);
        System.out.println("Año " + primerReporte.getAnio() + " Mes " + primerReporte.getMes() + ":\n");

        // Agrupar por sede
        Map<String, List<ReporteSedeDTO>> agrupadoPorSede = new LinkedHashMap<>();
        for (ReporteSedeDTO r : listado) {
            String claveSede = r.getSede().getNombre() + " - " + r.getSede().getDistrito();
            agrupadoPorSede.computeIfAbsent(claveSede, k -> new ArrayList<>()).add(r);
        }

        // Imprimir agrupado
        for (Map.Entry<String, List<ReporteSedeDTO>> entrada : agrupadoPorSede.entrySet()) {
            System.out.println("Sede: " + entrada.getKey());
            for (ReporteSedeDTO r : entrada.getValue()) {
                String nombreCompleto = r.getPersona().getNombre() + " "
                        + r.getPersona().getPaterno() + " "
                        + r.getPersona().getMaterno();

                System.out.println("Persona: " + nombreCompleto + ", Préstamo ID: "
                        + r.getPrestamo().getIdPrestamo() + ", Fecha préstamo: "
                        + r.getPrestamo().getFechaPrestamo());
            }
            System.out.println("--------------------------------------------------");
        }
        
        
        //        for (ReporteSedeDTO r : listado) {
//            String nombreCompleto = r.getPersona().getNombre() + " "
//                    + r.getPersona().getPaterno() + " "
//                    + r.getPersona().getMaterno();
//
//            String nombreSede = r.getSede().getNombre() + " - " + r.getSede().getDistrito();
//
//            System.out.println("Sede: " + nombreSede + " | Año: " + r.getAnio() + " | Mes: " + r.getMes());
//            System.out.println("Persona: " + nombreCompleto + " | Préstamo ID: "
//                    + r.getPrestamo().getIdPrestamo() + " | Fecha préstamo: "
//                    + r.getPrestamo().getFechaPrestamo());
//            System.out.println("--------------------------------------------------");
//        }
    }

}
