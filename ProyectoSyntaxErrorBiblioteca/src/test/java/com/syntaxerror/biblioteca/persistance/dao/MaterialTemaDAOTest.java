package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.TemaDTO;
import com.syntaxerror.biblioteca.model.CreadorDTO;
import com.syntaxerror.biblioteca.model.enums.TipoAutor;
import com.syntaxerror.biblioteca.model.enums.Categoria;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialTemaDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.CreadorDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.TemaDAOImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test unitario para MaterialTemaDAOImpl y CreadorDAOImpl
 * Prueba las operaciones CRUD y las relaciones entre Material y Tema
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialTemaDAOTest {

    private final MaterialTemaDAO materialTemaDAO;
    private final CreadorDAO creadorDAO;
    private final MaterialDAO materialDAO;
    private final TemaDAO temaDAO;
    
    // Constantes para Material
    private static final String MATERIAL1_TITULO = "English Grammar in Use";
    private static final String MATERIAL1_EDICION = "5th Edition";
    private static final NivelDeIngles MATERIAL1_NIVEL = NivelDeIngles.AVANZADO;
    private static final Integer MATERIAL1_ANIO = 2019;
    
    // Constantes para Tema
    private static final String TEMA1_DESCRIPCION = "Terror";
    private static final Categoria TEMA1_CATEGORIA = Categoria.GENERO;
    private static final String TEMA2_DESCRIPCION = "Mayores de 14";
    private static final Categoria TEMA2_CATEGORIA = Categoria.EDAD;
    
    // Constantes para Creador
    private static final String CREADOR1_NOMBRE = "Raymond";
    private static final String CREADOR1_PATERNO = "Murphy";
    private static final String CREADOR1_MATERNO = "";
    private static final String CREADOR1_SEUDONIMO = "R. Murphy";
    private static final TipoAutor CREADOR1_TIPO = TipoAutor.AUTOR;
    private static final String CREADOR1_NACIONALIDAD = "British";

    public MaterialTemaDAOTest() {
        this.materialTemaDAO = new MaterialTemaDAOImpl();
        this.creadorDAO = new CreadorDAOImpl();
        this.materialDAO = new MaterialDAOImpl();
        this.temaDAO = new TemaDAOImpl();
    }

    @BeforeEach
    public void setUp() {
        limpiarBaseDeDatos();
    }

    @AfterEach
    public void tearDown() {
        limpiarBaseDeDatos();
    }

    /**
     * Test del método asociar
     * Verifica la correcta asociación entre Material y Tema
     */
    @Test
    @Order(1)
    public void testAsociar() {
        System.out.println("Test: asociar");
        
        // Crear y persistir material y tema de prueba
        MaterialDTO material = crearMaterial(null, MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);
        
        TemaDTO tema = crearTema(null, TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema = temaDAO.insertar(tema);
        tema.setIdTema(idTema);
        
        // Asociar material y tema
        Integer resultado = materialTemaDAO.asociar(idMaterial, idTema);
        assertNotNull(resultado, "El resultado de la asociación no debería ser null");
        assertTrue(resultado > 0, "La asociación debería ser exitosa");
        
        // Verificar que existe la relación
        assertTrue(materialTemaDAO.existeRelacion(idMaterial, idTema),
                "La relación debería existir después de asociar");
    }

    /**
     * Test del método desasociar
     * Verifica la correcta desasociación entre Material y Tema
     */
    @Test
    @Order(2)
    public void testDesasociar() {
        System.out.println("Test: desasociar");
        
        // Crear y persistir material y tema
        MaterialDTO material = crearMaterial(null, MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);
        
        TemaDTO tema = crearTema(null, TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema = temaDAO.insertar(tema);
        tema.setIdTema(idTema);
        
        // Asociar primero
        materialTemaDAO.asociar(idMaterial, idTema);
        
        // Desasociar
        Integer resultado = materialTemaDAO.desasociar(idMaterial, idTema);
        assertNotNull(resultado, "El resultado de la desasociación no debería ser null");
        assertTrue(resultado > 0, "La desasociación debería ser exitosa");
        
        // Verificar que no existe la relación
        assertFalse(materialTemaDAO.existeRelacion(idMaterial, idTema),
                "La relación no debería existir después de desasociar");
    }

    /**
     * Test del método listarTemasPorMaterial
     */
    @Test
    @Order(3)
    public void testListarTemasPorMaterial() {
        System.out.println("Test: listarTemasPorMaterial");
        
        // Crear y persistir material y temas
        MaterialDTO material = crearMaterial(null, MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);
        
        TemaDTO tema1 = crearTema(null, TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema1 = temaDAO.insertar(tema1);
        tema1.setIdTema(idTema1);
        
        TemaDTO tema2 = crearTema(null, TEMA2_DESCRIPCION, TEMA2_CATEGORIA);
        Integer idTema2 = temaDAO.insertar(tema2);
        tema2.setIdTema(idTema2);
        
        // Asociar material con ambos temas
        materialTemaDAO.asociar(idMaterial, idTema1);
        materialTemaDAO.asociar(idMaterial, idTema2);
        
        // Obtener lista de temas
        ArrayList<TemaDTO> temas = materialTemaDAO.listarTemasPorMaterial(idMaterial);
        assertEquals(2, temas.size(), "Deberían haber exactamente dos temas asociados");
    }

    /**
     * Test de los métodos CRUD de CreadorDAO
     */
    @Test
    @Order(4)
    public void testCreadorCRUD() {
        System.out.println("Test: CreadorCRUD");
        
        // Crear y guardar un creador
        CreadorDTO creador = crearCreador(null, CREADOR1_NOMBRE, CREADOR1_PATERNO, CREADOR1_MATERNO,
                CREADOR1_SEUDONIMO, CREADOR1_TIPO, CREADOR1_NACIONALIDAD, true);
        
        Integer idCreador = creadorDAO.insertar(creador);
        assertNotNull(idCreador, "El ID del creador no debería ser null");
        assertTrue(idCreador > 0, "El ID del creador debería ser mayor que 0");
        
        creador.setIdAutor(idCreador); // Establecer el ID generado
        
        // Obtener el creador y verificar datos
        CreadorDTO obtenido = creadorDAO.obtenerPorId(idCreador);
        assertNotNull(obtenido, "El creador obtenido no debería ser null");
        assertEquals(CREADOR1_NOMBRE, obtenido.getNombre());
        assertEquals(CREADOR1_SEUDONIMO, obtenido.getSeudonimo());
        
        // Modificar creador
        String nuevoSeudonimo = "Ray Murphy";
        obtenido.setSeudonimo(nuevoSeudonimo);
        Integer resultadoModificacion = creadorDAO.modificar(obtenido);
        assertTrue(resultadoModificacion > 0, "La modificación debería ser exitosa");
        
        // Verificar modificación
        CreadorDTO modificado = creadorDAO.obtenerPorId(idCreador);
        assertEquals(nuevoSeudonimo, modificado.getSeudonimo());
        
        // Eliminar creador
        Integer resultadoEliminacion = creadorDAO.eliminar(modificado);
        assertTrue(resultadoEliminacion > 0, "La eliminación debería ser exitosa");
        
        // Verificar eliminación
        CreadorDTO eliminado = creadorDAO.obtenerPorId(idCreador);
        assertNull(eliminado, "El creador eliminado no debería existir");
    }

    /**
     * Método auxiliar para crear un material
     */
    private MaterialDTO crearMaterial(Integer id, String titulo, String edicion, NivelDeIngles nivel, Integer anio) {
        MaterialDTO material = new MaterialDTO();
        material.setIdMaterial(id);
        material.setTitulo(titulo);
        material.setEdicion(edicion);
        material.setNivel(nivel);
        material.setAnioPublicacion(anio);
        return material;
    }

    /**
     * Método auxiliar para crear un tema
     */
    private TemaDTO crearTema(Integer id, String descripcion, Categoria categoria) {
        TemaDTO tema = new TemaDTO();
        tema.setIdTema(id);
        tema.setDescripcion(descripcion);
        tema.setCategoria(categoria);
        return tema;
    }

    /**
     * Método auxiliar para crear un creador
     */
    private CreadorDTO crearCreador(Integer id, String nombre, String paterno, String materno,
            String seudonimo, TipoAutor tipo, String nacionalidad, boolean activo) {
        CreadorDTO creador = new CreadorDTO();
        creador.setIdAutor(id);
        creador.setNombre(nombre);
        creador.setPaterno(paterno);
        creador.setMaterno(materno);
        creador.setSeudonimo(seudonimo);
        creador.setTipo(tipo);
        creador.setNacionalidad(nacionalidad);
        creador.setActivo(activo);
        return creador;
    }

    /**
     * Método auxiliar para limpiar la base de datos
     */
    private void limpiarBaseDeDatos() {
        try {
            // Primero eliminar todas las relaciones Material-Tema
            ArrayList<MaterialDTO> materiales = materialTemaDAO.listarMaterialesPorTema(1); // Usar un ID válido en lugar de null
            if (materiales != null) {
                for (MaterialDTO material : materiales) {
                    ArrayList<TemaDTO> temas = materialTemaDAO.listarTemasPorMaterial(material.getIdMaterial());
                    if (temas != null) {
                        for (TemaDTO tema : temas) {
                            materialTemaDAO.desasociar(material.getIdMaterial(), tema.getIdTema());
                        }
                    }
                }
            }

            // Luego eliminar todos los creadores
            ArrayList<CreadorDTO> creadores = creadorDAO.listarTodos();
            if (creadores != null) {
                for (CreadorDTO creador : creadores) {
                    creadorDAO.eliminar(creador);
                }
            }
        } catch (Exception e) {
            // Ignorar errores durante la limpieza
            System.out.println("Error durante la limpieza de la base de datos: " + e.getMessage());
        }
    }
} 