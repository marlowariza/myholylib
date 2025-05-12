/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.EjemplarDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.SedeDTO;
import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.enums.FormatoDigital;
import com.syntaxerror.biblioteca.model.enums.TipoEjemplar;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.EjemplarDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

/**
 * Test class for EjemplarDAO implementation
 * Prueba todas las operaciones CRUD sobre la tabla BIB_EJEMPLAR
 * Verifica las restricciones de negocio:
 * - Ejemplares físicos deben tener FormatoDigital null
 * - Ejemplares digitales deben tener FormatoDigital no null
 * - Validación de IDs y objetos nulos
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EjemplarDAOTest {

    private final EjemplarDAO ejemplarDAO;
    private final SedeDAO sedeDAO;
    private final MaterialDAO materialDAO;
    private final EditorialDAO editorialDAO;
    private SedeDTO sede;
    private MaterialDTO material;

    public EjemplarDAOTest() {
        this.ejemplarDAO = new EjemplarDAOImpl();
        this.sedeDAO = new SedeDAOImpl();
        this.materialDAO = new MaterialDAOImpl();
        this.editorialDAO = new EditorialDAOImpl();
    }

    @BeforeEach
    public void setUp() {
        limpiarBaseDeDatos();
        this.sede = crearSedePrueba();
        this.material = crearMaterialPrueba();
    }

    @AfterEach
    public void tearDown() {
        limpiarBaseDeDatos();
    }

    /**
     * Método auxiliar para limpiar la base de datos
     * Elimina todos los ejemplares y sus dependencias
     */
    private void limpiarBaseDeDatos() {
        // Primero eliminar ejemplares (por las FK)
        ArrayList<EjemplarDTO> ejemplares = ejemplarDAO.listarTodos();
        for (EjemplarDTO ejemplar : ejemplares) {
            ejemplarDAO.eliminar(ejemplar);
        }
        
        // Luego eliminar materiales
        ArrayList<MaterialDTO> materiales = materialDAO.listarTodos();
        for (MaterialDTO material : materiales) {
            materialDAO.eliminar(material);
        }
        
        // Luego eliminar editoriales
        ArrayList<EditorialDTO> editoriales = editorialDAO.listarTodos();
        for (EditorialDTO editorial : editoriales) {
            editorialDAO.eliminar(editorial);
        }
        
        // Finalmente eliminar sedes
        ArrayList<SedeDTO> sedes = sedeDAO.listarTodos();
        for (SedeDTO sede : sedes) {
            sedeDAO.eliminar(sede);
        }
        
        // Verificar limpieza
        assertTrue(ejemplarDAO.listarTodos().isEmpty(), 
                  "La base de datos de ejemplares debería estar vacía después de la limpieza");
    }

    private SedeDTO crearSedePrueba() {
        SedeDTO sede = new SedeDTO();
        sede.setNombre("Sede Test");
        sede.setDireccion("Dirección Test");
        sede.setDistrito("Distrito Test");
        sede.setTelefonoContacto("123456789");
        sede.setCorreoContacto("test@test.com");
        sede.setActiva(true);

        Integer idSede = sedeDAO.insertar(sede);
        assertNotNull(idSede, "El ID de sede generado no debería ser null");
        sede.setIdSede(idSede);
        return sede;
    }

    private EditorialDTO crearEditorialPrueba() {
        EditorialDTO editorial = new EditorialDTO();
        editorial.setNombre("Editorial Test");
        editorial.setPais("País Test");
        editorial.setSitioWeb("http://test.com");

        Integer idEditorial = editorialDAO.insertar(editorial);
        assertNotNull(idEditorial, "El ID de editorial generado no debería ser null");
        editorial.setIdEditorial(idEditorial);
        return editorial;
    }

    private MaterialDTO crearMaterialPrueba() {
        MaterialDTO material = new MaterialDTO();
        material.setTitulo("Material Test");
        material.setEdicion("Primera Edición");
        material.setNivel(NivelDeIngles.INTERMEDIO);
        material.setAnioPublicacion(2024);
        material.setEditorial(crearEditorialPrueba());

        Integer idMaterial = materialDAO.insertar(material);
        assertNotNull(idMaterial, "El ID de material generado no debería ser null");
        material.setIdMaterial(idMaterial);
        return material;
    }

    private EjemplarDTO crearEjemplarFisico(String ubicacion) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        ejemplar.setFechaAdquisicion(new Date(System.currentTimeMillis()));
        ejemplar.setDisponible(true);
        ejemplar.setTipo(TipoEjemplar.FISICO);
        ejemplar.setFormatoDigital(null);
        ejemplar.setUbicacion(ubicacion);
        ejemplar.setSede(sede);
        ejemplar.setMaterial(material);
        return ejemplar;
    }

    private EjemplarDTO crearEjemplarDigital(String ubicacion, FormatoDigital formato) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        ejemplar.setFechaAdquisicion(new Date(System.currentTimeMillis()));
        ejemplar.setDisponible(true);
        ejemplar.setTipo(TipoEjemplar.DIGITAL);
        ejemplar.setFormatoDigital(formato);
        ejemplar.setUbicacion(ubicacion);
        ejemplar.setSede(sede);
        ejemplar.setMaterial(material);
        return ejemplar;
    }

    @Test
    @Order(1)
    public void testInsertarEjemplarFisico() {
        System.out.println("insertar ejemplar físico");
        
        EjemplarDTO ejemplar = crearEjemplarFisico("Estante A-1");
        Integer id = ejemplarDAO.insertar(ejemplar);
        
        assertNotNull(id, "El ID generado no debería ser null");
        assertTrue(id > 0, "El ID generado debería ser mayor que 0");
        
        EjemplarDTO recuperado = ejemplarDAO.obtenerPorId(id);
        assertNotNull(recuperado, "El ejemplar físico debería existir");
        assertEquals(TipoEjemplar.FISICO, recuperado.getTipo());
        assertNull(recuperado.getFormatoDigital(), "El formato digital debe ser null para ejemplares físicos");
        assertEquals("Estante A-1", recuperado.getUbicacion());
    }

    @Test
    @Order(2)
    public void testInsertarEjemplarDigital() {
        System.out.println("insertar ejemplar digital");
        
        EjemplarDTO ejemplar = crearEjemplarDigital("Servidor-01", FormatoDigital.PDF);
        Integer id = ejemplarDAO.insertar(ejemplar);
        
        assertNotNull(id, "El ID generado no debería ser null");
        assertTrue(id > 0, "El ID generado debería ser mayor que 0");
        
        EjemplarDTO recuperado = ejemplarDAO.obtenerPorId(id);
        assertNotNull(recuperado, "El ejemplar digital debería existir");
        assertEquals(TipoEjemplar.DIGITAL, recuperado.getTipo());
        assertEquals(FormatoDigital.PDF, recuperado.getFormatoDigital());
        assertEquals("Servidor-01", recuperado.getUbicacion());
    }

    @Test
    @Order(3)
    public void testInsertarEjemplarDigitalSinFormato() {
        System.out.println("insertar ejemplar digital sin formato");
        
        EjemplarDTO ejemplar = crearEjemplarDigital("Servidor-02", null);
        
        ejemplarDAO.insertar(ejemplar);
    }

    @Test
    @Order(4)
    public void testInsertarEjemplarFisicoConFormato() {
        System.out.println("insertar ejemplar físico con formato");
        
        EjemplarDTO ejemplar = crearEjemplarFisico("Estante B-2");
        ejemplar.setFormatoDigital(FormatoDigital.PDF);
        
        ejemplarDAO.insertar(ejemplar);

    }

    @Test
    @Order(5)
    public void testModificarTipoEjemplar() {
        System.out.println("modificar tipo de ejemplar");
        
        // Crear ejemplar físico
        EjemplarDTO ejemplar = crearEjemplarFisico("Estante C-3");
        Integer id = ejemplarDAO.insertar(ejemplar);
        ejemplar.setIdEjemplar(id);
        
        // Modificar a digital
        ejemplar.setTipo(TipoEjemplar.DIGITAL);
        ejemplar.setFormatoDigital(FormatoDigital.PDF);
        ejemplar.setUbicacion("Servidor-03");
        
        Integer resultado = ejemplarDAO.modificar(ejemplar);
        assertTrue(resultado > 0, "La modificación debería ser exitosa");
        
        // Verificar cambios
        EjemplarDTO modificado = ejemplarDAO.obtenerPorId(id);
        assertEquals(TipoEjemplar.DIGITAL, modificado.getTipo());
        assertEquals(FormatoDigital.PDF, modificado.getFormatoDigital());
        assertEquals("Servidor-03", modificado.getUbicacion());
    }

    @Test
    @Order(6)
    public void testEliminarEjemplar() {
        System.out.println("eliminar ejemplar");
        
        EjemplarDTO ejemplar = crearEjemplarFisico("Estante D-4");
        Integer id = ejemplarDAO.insertar(ejemplar);
        ejemplar.setIdEjemplar(id);
        
        Integer resultado = ejemplarDAO.eliminar(ejemplar);
        assertTrue(resultado > 0, "La eliminación debería ser exitosa");
        
        assertNull(ejemplarDAO.obtenerPorId(id), 
                  "El ejemplar no debería existir después de eliminarlo");
    }

    @Test
    @Order(7)
    public void testValidacionesDeID() {
        System.out.println("validaciones de ID");
        
        // Obtener por ID null
        assertThrows(IllegalArgumentException.class, () -> {
            ejemplarDAO.obtenerPorId(null);
        });
        
        // Obtener por ID negativo
        assertThrows(IllegalArgumentException.class, () -> {
            ejemplarDAO.obtenerPorId(-1);
        });
        
        // Modificar con ID null
        EjemplarDTO ejemplar = crearEjemplarFisico("Estante E-5");
        assertThrows(IllegalArgumentException.class, () -> {
            ejemplarDAO.modificar(ejemplar);
        });
        
        // Eliminar con ID null
        assertThrows(IllegalArgumentException.class, () -> {
            ejemplarDAO.eliminar(ejemplar);
        });
    }

    @Test
    @Order(8)
    public void testListarTodos() {
        System.out.println("listar todos");
        
        assertTrue(ejemplarDAO.listarTodos().isEmpty(), 
                  "La lista debería estar vacía inicialmente");
        
        // Insertar ejemplares de prueba
        ejemplarDAO.insertar(crearEjemplarFisico("Estante F-6"));
        ejemplarDAO.insertar(crearEjemplarDigital("Servidor-04", FormatoDigital.PDF));
        
        ArrayList<EjemplarDTO> ejemplares = ejemplarDAO.listarTodos();
        assertEquals(2, ejemplares.size(), "Deberían haber dos ejemplares");
        
        boolean encontroFisico = false;
        boolean encontroDigital = false;
        
        for (EjemplarDTO ejemplar : ejemplares) {
            if (ejemplar.getTipo() == TipoEjemplar.FISICO) {
                encontroFisico = true;
                assertNull(ejemplar.getFormatoDigital(), 
                         "El ejemplar físico no debe tener formato digital");
            } else {
                encontroDigital = true;
                assertNotNull(ejemplar.getFormatoDigital(), 
                            "El ejemplar digital debe tener formato digital");
            }
        }
        
        assertTrue(encontroFisico, "Debería existir un ejemplar físico");
        assertTrue(encontroDigital, "Debería existir un ejemplar digital");
    }
}
