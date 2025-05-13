
package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.EditorialDAO;
import com.syntaxerror.biblioteca.persistance.dao.MaterialDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.EditorialDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.MaterialDAOImpl;
import java.util.List;

public class MaterialBO {

    private MaterialDAO materialDAO;
    private EditorialDAO editorialDAO;

    public MaterialBO() {
        this.materialDAO = new MaterialDAOImpl();
        this.editorialDAO = new EditorialDAOImpl();
    }

    public int insertar(String titulo, String edicion, NivelDeIngles nivel, Integer anioPublicacion, Integer idEditorial) {
        //Aqui no estoy seguro si el parametro de la fk es Integer u Objeto
        MaterialDTO material = new MaterialDTO();         
        material.setTitulo(titulo);
        material.setEdicion(edicion);
        material.setNivel(nivel);
        material.setAnioPublicacion(anioPublicacion);
        
        EditorialDTO editorial = this.editorialDAO.obtenerPorId(idEditorial);
        
        editorial.setIdEditorial(idEditorial);
        material.setEditorial(editorial);

        return this.materialDAO.insertar(material);
    }

    public int modificar(Integer idMaterial, String titulo, String edicion, NivelDeIngles nivel, Integer anioPublicacion,Integer idEditorial) {
        MaterialDTO material = new MaterialDTO();
        material.setIdMaterial(idMaterial);
        material.setTitulo(titulo);
        material.setEdicion(edicion);
        material.setNivel(nivel);
        material.setAnioPublicacion(anioPublicacion);

        EditorialDTO editorial = this.editorialDAO.obtenerPorId(idEditorial);
        
        editorial.setIdEditorial(idEditorial);
        material.setEditorial(editorial);

        return this.materialDAO.modificar(material);
    }

    public int eliminar(int idMaterial) {
        MaterialDTO material = new MaterialDTO();
        material.setIdMaterial(idMaterial);
        return this.materialDAO.eliminar(material);
    }

    public MaterialDTO obtenerPorId(Integer idMaterial) {
        return this.materialDAO.obtenerPorId(idMaterial);
    }

    public List<MaterialDTO> listarTodos() {
        return this.materialDAO.listarTodos();
    }
}

