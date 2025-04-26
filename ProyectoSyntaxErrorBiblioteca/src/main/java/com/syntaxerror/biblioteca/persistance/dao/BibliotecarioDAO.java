
package com.syntaxerror.biblioteca.persistance.dao;
import com.syntaxerror.biblioteca.model.BibliotecarioDTO;
import java.util.ArrayList;

public interface BibliotecarioDAO {
    public Integer insertar(BibliotecarioDTO bibliotecario);
    public BibliotecarioDTO obtenerPorId(Integer id);
    public Integer modificar(BibliotecarioDTO bibliotecario);
    public Integer eliminar(BibliotecarioDTO bibliotecario);
    public ArrayList<BibliotecarioDTO> listarTodos();
}
