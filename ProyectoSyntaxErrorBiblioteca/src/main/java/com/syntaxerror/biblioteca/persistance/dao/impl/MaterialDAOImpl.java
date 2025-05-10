package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.EditorialDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.enums.NivelDeIngles;
import com.syntaxerror.biblioteca.persistance.dao.MaterialDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl extends DAOImplBase implements MaterialDAO {

    private MaterialDTO material;

    public MaterialDAOImpl() {
        super("BIB_MATERIAL");
        this.retornarLlavePrimaria = true;
        this.material = null;
    }
    
    @Override
    public Integer eliminarPorEditorial(Integer editorialId) {
        int resultado = 0;
        try {
            this.iniciarTransaccion();
            
            // Verificar si existen materiales asociados a la editorial
            String checkSql = "SELECT COUNT(*) FROM BIB_MATERIAL WHERE EDITORIAL_IDEDITORIAL = ?";
            this.colocarSQLenStatement(checkSql);
            this.statement.setInt(1, editorialId);

            // Ejecutar la consulta de verificación
            ResultSet aux = this.statement.executeQuery();
            
            if (aux.next()) {
                int cantMateriales = aux.getInt(1);

                if (cantMateriales == 0) {
                    System.out.println("No existen materiales asociados a la editorial con ID: " + editorialId);
                    return 0; // No hay materiales para eliminar
                }
            }
            
            String sql = "DELETE FROM BIB_MATERIAL WHERE EDITORIAL_IDEDITORIAL = ?";
            this.colocarSQLenStatement(sql);

            this.statement.setInt(1, editorialId);

            resultado = this.ejecutarModificacionEnBD();

            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar consulta - ELIMINAR: " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }

        return resultado;
    }


    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_MATERIAL", true, true));
        this.listaColumnas.add(new Columna("TITULO", false, false));
        this.listaColumnas.add(new Columna("EDICION", false, false));
        this.listaColumnas.add(new Columna("NIVEL", false, false));
        this.listaColumnas.add(new Columna("ANHIO_PUBLICACION", false, false));
        this.listaColumnas.add(new Columna("EDITORIAL_IDEDITORIAL", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {

        //si es autoincremental, se salta el (1,ID)
        this.statement.setString(1, this.material.getTitulo());
        this.statement.setString(2, this.material.getEdicion());
        this.statement.setString(3, this.material.getNivel().name());
        this.statement.setInt(4, this.material.getAnioPublicacion());
        this.statement.setInt(5, this.material.getEditorial().getIdEditorial());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setString(1, this.material.getTitulo());
        this.statement.setString(2, this.material.getEdicion());
        this.statement.setString(3, this.material.getNivel().name());
        this.statement.setInt(4, this.material.getAnioPublicacion());
        this.statement.setInt(5, this.material.getEditorial().getIdEditorial());
        this.statement.setInt(6, this.material.getIdMaterial());
        //En modificar el ID va al ultimo
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.material.getIdMaterial());
        //Para eliminar solo va el id
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.material.getIdMaterial());
        //Para obtener por Id igual solo el id
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.material = new MaterialDTO();
        this.material.setIdMaterial(this.resultSet.getInt("ID_MATERIAL"));
        this.material.setTitulo(this.resultSet.getString("TITULO"));
        this.material.setEdicion(this.resultSet.getString("EDICION"));
        this.material.setNivel(NivelDeIngles.valueOf(this.resultSet.getString("NIVEL")));
        this.material.setAnioPublicacion(this.resultSet.getInt("ANHIO_PUBLICACION"));

        // Crear objetos DTO básicos para las relaciones
        EditorialDTO editorial = new EditorialDTO();
        editorial.setIdEditorial(this.resultSet.getInt("EDITORIAL_IDEDITORIAL"));
        this.material.setEditorial(editorial);

    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.material = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.material);
    }

    @Override
    public Integer insertar(MaterialDTO material) {
        this.material = material;
        return super.insertar();
    }

    @Override
    public MaterialDTO obtenerPorId(Integer idMaterial) {
        this.material = new MaterialDTO();
        this.material.setIdMaterial(idMaterial);
        super.obtenerPorId();
        return this.material;
    }

    @Override
    public ArrayList<MaterialDTO> listarTodos() {
        return (ArrayList<MaterialDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(MaterialDTO material) {
        this.material = material;
        return super.modificar();
    }

    @Override
    public Integer eliminar(MaterialDTO material) {
        this.material = material;
        return super.eliminar();
    }
}
