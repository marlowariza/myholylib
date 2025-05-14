package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.TemaDTO;
import com.syntaxerror.biblioteca.model.CreadorDTO;
import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.enums.TipoAutor;
import com.syntaxerror.biblioteca.model.enums.Categoria;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialTemaDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.CreadorDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
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
 * Test unitario para MaterialTemaDAOImpl y CreadorDAOImpl Prueba las
 * operaciones CRUD y las relaciones entre Material y Tema
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialTemaDAOTest {

    private final MaterialTemaDAO materialTemaDAO;
    private final CreadorDAO creadorDAO;
    private final MaterialDAO materialDAO;
    private final TemaDAO temaDAO;
    private final EditorialDAO editorialDAO;

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
        this.editorialDAO = new EditorialDAOImpl();
    }

    @BeforeEach
    public void setUp() {
        limpiarBaseDeDatos();
    }

    @AfterEach
    public void tearDown() {
        limpiarBaseDeDatos();
    }

    @Test
    @Order(1)
    public void testAsociar() {
        MaterialDTO material = crearMaterial(MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);

        TemaDTO tema = crearTema(TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema = temaDAO.insertar(tema);
        tema.setIdTema(idTema);

        Integer resultado = materialTemaDAO.asociar(idMaterial, idTema);
        assertNotNull(resultado);
        assertTrue(resultado > 0);
        assertTrue(materialTemaDAO.existeRelacion(idMaterial, idTema));
        
        imprimirRelacionesMaterialTema();
    }

    @Test
    @Order(2)
    public void testDesasociar() {
        MaterialDTO material = crearMaterial(MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);

        TemaDTO tema = crearTema(TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema = temaDAO.insertar(tema);
        tema.setIdTema(idTema);

        materialTemaDAO.asociar(idMaterial, idTema);
        Integer resultado = materialTemaDAO.desasociar(idMaterial, idTema);
        assertNotNull(resultado);
        assertTrue(resultado > 0);
        assertFalse(materialTemaDAO.existeRelacion(idMaterial, idTema));
    }

    @Test
    @Order(3)
    public void testListarTemasPorMaterial() {
        MaterialDTO material = crearMaterial(MATERIAL1_TITULO, MATERIAL1_EDICION, MATERIAL1_NIVEL, MATERIAL1_ANIO);
        Integer idMaterial = materialDAO.insertar(material);
        material.setIdMaterial(idMaterial);

        TemaDTO tema1 = crearTema(TEMA1_DESCRIPCION, TEMA1_CATEGORIA);
        Integer idTema1 = temaDAO.insertar(tema1);
        tema1.setIdTema(idTema1);

        TemaDTO tema2 = crearTema(TEMA2_DESCRIPCION, TEMA2_CATEGORIA);
        Integer idTema2 = temaDAO.insertar(tema2);
        tema2.setIdTema(idTema2);

        materialTemaDAO.asociar(idMaterial, idTema1);
        materialTemaDAO.asociar(idMaterial, idTema2);

        ArrayList<TemaDTO> temas = materialTemaDAO.listarTemasPorMaterial(idMaterial);
        assertEquals(2, temas.size());
    }

    @Test
    @Order(4)
    public void testCreadorCRUD() {
        CreadorDTO creador = crearCreador(null, CREADOR1_NOMBRE, CREADOR1_PATERNO, CREADOR1_MATERNO,
                CREADOR1_SEUDONIMO, CREADOR1_TIPO, CREADOR1_NACIONALIDAD, true);
        Integer idCreador = creadorDAO.insertar(creador);
        assertNotNull(idCreador);
        assertTrue(idCreador > 0);

        creador.setIdAutor(idCreador);
        CreadorDTO obtenido = creadorDAO.obtenerPorId(idCreador);
        assertNotNull(obtenido);
        assertEquals(CREADOR1_NOMBRE, obtenido.getNombre());

        obtenido.setSeudonimo("Ray Murphy");
        assertTrue(creadorDAO.modificar(obtenido) > 0);

        CreadorDTO modificado = creadorDAO.obtenerPorId(idCreador);
        assertEquals("Ray Murphy", modificado.getSeudonimo());

        assertTrue(creadorDAO.eliminar(modificado) > 0);
        assertNull(creadorDAO.obtenerPorId(idCreador));
    }

    private MaterialDTO crearMaterial(String titulo, String edicion, NivelDeIngles nivel, Integer anio) {
        MaterialDTO material = new MaterialDTO();
        material.setTitulo(titulo);
        material.setEdicion(edicion);
        material.setNivel(nivel);
        material.setAnioPublicacion(anio);

        EditorialDTO editorial = new EditorialDTO();
        editorial.setNombre("Test Editorial");
        editorial.setPais("Perú");
        Integer idEditorial = editorialDAO.insertar(editorial);
        editorial.setIdEditorial(idEditorial);

        material.setEditorial(editorial);
        return material;
    }

    private TemaDTO crearTema(String descripcion, Categoria categoria) {
        TemaDTO tema = new TemaDTO();
        tema.setDescripcion(descripcion);
        tema.setCategoria(categoria);
        return tema;
    }

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

    private void limpiarBaseDeDatos() {
        try {
            for (MaterialDTO material : materialDAO.listarTodos()) {
                for (TemaDTO tema : materialTemaDAO.listarTemasPorMaterial(material.getIdMaterial())) {
                    materialTemaDAO.desasociar(material.getIdMaterial(), tema.getIdTema());
                }
                materialDAO.eliminar(material);
            }

            for (TemaDTO tema : temaDAO.listarTodos()) {
                temaDAO.eliminar(tema);
            }

            for (CreadorDTO creador : creadorDAO.listarTodos()) {
                creadorDAO.eliminar(creador);
            }

            for (EditorialDTO editorial : editorialDAO.listarTodos()) {
                editorialDAO.eliminar(editorial);
            }
        } catch (Exception e) {
            System.out.println("Error durante la limpieza: " + e.getMessage());
        }
    }

    private void imprimirRelacionesMaterialTema() {
        try {
            ArrayList<MaterialDTO> materiales = materialDAO.listarTodos();
            for (MaterialDTO material : materiales) {
                ArrayList<TemaDTO> temas = materialTemaDAO.listarTemasPorMaterial(material.getIdMaterial());
                System.out.println("Material: " + material.getTitulo() + " (ID: " + material.getIdMaterial() + ")");
                for (TemaDTO tema : temas) {
                    System.out.println("   -> Tema: " + tema.getDescripcion() + " (ID: " + tema.getIdTema() + ")");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al imprimir relaciones: " + e.getMessage());
        }
    }

}
