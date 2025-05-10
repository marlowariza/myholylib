package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.model.TemaDTO;
import com.syntaxerror.biblioteca.model.enums.Categoria;
import com.syntaxerror.biblioteca.persistance.dao.TemaDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.TemaDAOImpl;
import java.util.ArrayList;

public class TemaBO {

    private TemaDAO temaDAO;

    public TemaBO() {
        this.temaDAO = new TemaDAOImpl();
    }

    public int insertar(String descripcion, Categoria categoria, Integer idTemaPadre) {
        TemaDTO tema = new TemaDTO();
        tema.setDescripcion(descripcion);
        tema.setCategoria(categoria);

        if (idTemaPadre != null) {
            TemaDTO temaPadre = new TemaDTO();
            temaPadre.setIdTema(idTemaPadre);
            tema.setTemaPadre(temaPadre);
        }

        return this.temaDAO.insertar(tema);
    }

    public int modificar(Integer idTema, String descripcion, Categoria categoria, Integer idTemaPadre) {
        TemaDTO tema = new TemaDTO();
        tema.setIdTema(idTema);
        tema.setDescripcion(descripcion);
        tema.setCategoria(categoria);

        if (idTemaPadre != null) {
            TemaDTO temaPadre = new TemaDTO();
            temaPadre.setIdTema(idTemaPadre);
            tema.setTemaPadre(temaPadre);
        }

        return this.temaDAO.modificar(tema);
    }

    public int eliminar(Integer idTema) {
        TemaDTO tema = new TemaDTO();
        tema.setIdTema(idTema);
        return this.temaDAO.eliminar(tema);
    }

    public TemaDTO obtenerPorId(Integer idTema) {
        return this.temaDAO.obtenerPorId(idTema);
    }

    public ArrayList<TemaDTO> listarTodos() {
        return this.temaDAO.listarTodos();
    }
}
