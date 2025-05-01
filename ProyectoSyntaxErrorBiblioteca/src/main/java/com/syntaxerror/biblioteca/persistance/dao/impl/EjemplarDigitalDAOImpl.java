package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.model.EjemplarDigitalDTO;
import com.syntaxerror.biblioteca.model.MaterialDTO;
import com.syntaxerror.biblioteca.model.enums.FormatoDigital;
import com.syntaxerror.biblioteca.persistance.dao.EjemplarDigitalDAO;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjemplarDigitalDAOImpl extends DAOImplBase implements EjemplarDigitalDAO {

    private EjemplarDigitalDTO ejemplarDigital;

    public EjemplarDigitalDAOImpl() {
        super("BIB_EJEMPLAR_DIGITAL");
        this.retornarLlavePrimaria = true;
        this.ejemplarDigital = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_EJEMPLAR_DIG", true, true));
        this.listaColumnas.add(new Columna("FORMATO", false, false));
        this.listaColumnas.add(new Columna("MATERIAL_IDMATERIAL", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {

        //si es autoincremental, se salta el (1,ID)
        this.statement.setString(1, this.ejemplarDigital.getFormato().name());
        this.statement.setInt(2, this.ejemplarDigital.getMaterial().getIdMaterial());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setString(1, this.ejemplarDigital.getFormato().name());
        this.statement.setInt(2, this.ejemplarDigital.getMaterial().getIdMaterial());
        this.statement.setInt(3, this.ejemplarDigital.getIdEjemplarD());
        //En modificar el ID va al ultimo
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.ejemplarDigital.getIdEjemplarD());
        //Para eliminar solo va el id
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.ejemplarDigital.getIdEjemplarD());
        //Para obtener por Id igual solo el id
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.ejemplarDigital = new EjemplarDigitalDTO();
        this.ejemplarDigital.setIdEjemplarD(this.resultSet.getInt("ID_EJEMPLAR_DIG"));
        this.ejemplarDigital.setFormato(FormatoDigital.valueOf(this.resultSet.getString("FORMATO")));

        // Crear objetos DTO básicos para las relaciones
        MaterialDTO material = new MaterialDTO();
        material.setIdMaterial(this.resultSet.getInt("MATERIAL_IDMATERIAL"));
        this.ejemplarDigital.setMaterial(material);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.ejemplarDigital = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.ejemplarDigital);
    }

    @Override
    public Integer insertar(EjemplarDigitalDTO ejemplarDigital) {
        this.ejemplarDigital = ejemplarDigital;
        return super.insertar();
    }

    @Override
    public EjemplarDigitalDTO obtenerPorId(Integer idEjemplarDigital) {
        this.ejemplarDigital = new EjemplarDigitalDTO();
        this.ejemplarDigital.setIdEjemplarD(idEjemplarDigital);
        super.obtenerPorId();
        return this.ejemplarDigital;
    }

    @Override
    public ArrayList<EjemplarDigitalDTO> listarTodos() {
        return (ArrayList<EjemplarDigitalDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(EjemplarDigitalDTO ejemplarDigital) {
        this.ejemplarDigital = ejemplarDigital;
        return super.modificar();
    }

    @Override
    public Integer eliminar(EjemplarDigitalDTO ejemplarDigital) {
        this.ejemplarDigital = ejemplarDigital;
        return super.eliminar();
    }
}
