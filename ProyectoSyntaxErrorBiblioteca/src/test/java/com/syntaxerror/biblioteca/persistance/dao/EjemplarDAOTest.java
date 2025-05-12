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
 * Los IDs son autoincrementales, manejados por la base de datos
 * Maneja formatos digitales nulos para ejemplares físicos
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
        // Crear datos necesarios para las pruebas
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
        ejemplar.setFechaAdquisicion(Date.valueOf("2024-03-15"));
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
        ejemplar.setFechaAdquisicion(Date.valueOf("2024-03-15"));
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
    public void testInsertar() {
        System.out.println("insertar");
        
        // Crear y guardar ejemplar físico
        EjemplarDTO ejemplarFisico = crearEjemplarFisico("Ubicación Test 1");
        Integer id1 = ejemplarDAO.insertar(ejemplarFisico);
        assertNotNull(id1, "El ID generado no debería ser null");
        assertTrue(id1 > 0, "El ID generado debería ser mayor que 0");
        
        // Verificar que se guardó correctamente
        EjemplarDTO recuperado = ejemplarDAO.obtenerPorId(id1);
        assertNotNull(recuperado, "El ejemplar físico debería existir");
        assertEquals(TipoEjemplar.FISICO, recuperado.getTipo());
        assertNull(recuperado.getFormatoDigital());
        assertEquals("Ubicación Test 1", recuperado.getUbicacion());
        
        // Crear y guardar ejemplar digital
        EjemplarDTO ejemplarDigital = crearEjemplarDigital("Ubicación Test 2", FormatoDigital.PDF);
        Integer id2 = ejemplarDAO.insertar(ejemplarDigital);
        assertNotNull(id2, "El ID generado no debería ser null");
        assertTrue(id2 > id1, "El segundo ID debería ser mayor que el primero (autoincremental)");
        
        // Verificar que se guardó correctamente
        recuperado = ejemplarDAO.obtenerPorId(id2);
        assertNotNull(recuperado, "El ejemplar digital debería existir");
        assertEquals(TipoEjemplar.DIGITAL, recuperado.getTipo());
        assertEquals(FormatoDigital.PDF, recuperado.getFormatoDigital());
        assertEquals("Ubicación Test 2", recuperado.getUbicacion());
        
        // Verificar cantidad total
        assertEquals(2, ejemplarDAO.listarTodos().size(), "Deberían haber dos ejemplares en total");
    }

    @Test
    @Order(2)
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        
        // Insertar y verificar ejemplar físico
        EjemplarDTO ejemplarFisico = crearEjemplarFisico("Ubicación Test Físico");
        Integer idFisico = ejemplarDAO.insertar(ejemplarFisico);
        assertNotNull(idFisico, "El ID generado no debería ser null");
        ejemplarFisico.setIdEjemplar(idFisico);
        
        EjemplarDTO recuperado = ejemplarDAO.obtenerPorId(idFisico);
        assertNotNull(recuperado, "El ejemplar físico debería existir");
        assertEquals(idFisico, recuperado.getIdEjemplar());
        assertEquals(ejemplarFisico.getUbicacion(), recuperado.getUbicacion());
        assertEquals(TipoEjemplar.FISICO, recuperado.getTipo());
        assertNull(recuperado.getFormatoDigital());
        
        // Verificar que un ID inexistente retorna null
        assertNull(ejemplarDAO.obtenerPorId(99999), "Un ID inexistente debería retornar null");
    }

    @Test
    @Order(3)
    public void testListarTodos() {
        System.out.println("listarTodos");
        
        // Verificar lista vacía inicial
        assertTrue(ejemplarDAO.listarTodos().isEmpty(), "La lista debería estar vacía inicialmente");
        
        // Insertar un ejemplar físico
        EjemplarDTO ejemplarFisico = crearEjemplarFisico("Ubicación Física");
        Integer idFisico = ejemplarDAO.insertar(ejemplarFisico);
        assertNotNull(idFisico, "El ID generado no debería ser null");
        
        // Insertar un ejemplar digital
        EjemplarDTO ejemplarDigital = crearEjemplarDigital("Ubicación Digital", FormatoDigital.PDF);
        Integer idDigital = ejemplarDAO.insertar(ejemplarDigital);
        assertNotNull(idDigital, "El ID generado no debería ser null");
        
        // Verificar lista
        ArrayList<EjemplarDTO> ejemplares = ejemplarDAO.listarTodos();
        assertEquals(2, ejemplares.size(), "Deberían haber dos ejemplares en total");
        
        // Verificar contenido
        boolean encontroFisico = false;
        boolean encontroDigital = false;
        
        for (EjemplarDTO ejemplar : ejemplares) {
            if (ejemplar.getTipo() == TipoEjemplar.FISICO) {
                encontroFisico = true;
                assertNull(ejemplar.getFormatoDigital(), "El ejemplar físico no debería tener formato digital");
            } else {
                encontroDigital = true;
                assertNotNull(ejemplar.getFormatoDigital(), "El ejemplar digital debería tener formato digital");
            }
        }
        
        assertTrue(encontroFisico, "Debería existir un ejemplar físico");
        assertTrue(encontroDigital, "Debería existir un ejemplar digital");
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("modificar");
        
        // Insertar ejemplar físico
        EjemplarDTO ejemplar = crearEjemplarFisico("Ubicación Original");
        Integer id = ejemplarDAO.insertar(ejemplar);
        assertNotNull(id, "El ID generado no debería ser null");
        ejemplar.setIdEjemplar(id);
        
        // Verificar inserción inicial
        EjemplarDTO recuperado = ejemplarDAO.obtenerPorId(id);
        assertNotNull(recuperado, "El ejemplar debería existir");
        assertEquals(TipoEjemplar.FISICO, recuperado.getTipo());
        assertNull(recuperado.getFormatoDigital());
        
        // Modificar a digital
        ejemplar.setTipo(TipoEjemplar.DIGITAL);
        ejemplar.setFormatoDigital(FormatoDigital.PDF);
        ejemplar.setUbicacion("Nueva Ubicación");
        Integer resultado = ejemplarDAO.modificar(ejemplar);
        assertTrue(resultado > 0, "La modificación debería ser exitosa");
        
        // Verificar modificación
        recuperado = ejemplarDAO.obtenerPorId(id);
        assertNotNull(recuperado, "El ejemplar debería existir después de la modificación");
        assertEquals(TipoEjemplar.DIGITAL, recuperado.getTipo());
        assertEquals(FormatoDigital.PDF, recuperado.getFormatoDigital());
        assertEquals("Nueva Ubicación", recuperado.getUbicacion());
    }

    @Test
    @Order(5)
    public void testEliminar() {
        System.out.println("eliminar");
        
        // Insertar ejemplar
        EjemplarDTO ejemplar = crearEjemplarFisico("Ubicación Test");
        Integer id = ejemplarDAO.insertar(ejemplar);
        assertNotNull(id, "El ID generado no debería ser null");
        ejemplar.setIdEjemplar(id);
        
        // Verificar que existe
        assertNotNull(ejemplarDAO.obtenerPorId(id), "El ejemplar debería existir antes de eliminarlo");
        
        // Eliminar
        Integer resultado = ejemplarDAO.eliminar(ejemplar);
        assertTrue(resultado > 0, "La eliminación debería ser exitosa");
        
        // Verificar que ya no existe
        assertNull(ejemplarDAO.obtenerPorId(id), "El ejemplar no debería existir después de eliminarlo");
    }
}
