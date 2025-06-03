/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import DAO.DAOException;
import DAO.IPlatilloIngredienteDAO;
import Modelo.Ingrediente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wyrnm
 */
public class IngredientesTableModel extends AbstractTableModel {

    // Propiedades
    private IPlatilloIngredienteDAO platilloIngredeinte;

    // Lista de elementos de tipo Titulo
    private List<Ingrediente> datos = new ArrayList<>();

    // Constructor con un parámetro
    public IngredientesTableModel(IPlatilloIngredienteDAO platilloIngredeinte) {
        this.platilloIngredeinte = platilloIngredeinte;
    }

    /**
     * Retorna el nombre de cada columna
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "IdIngrediente";
            case 1:
                return "Nombre";
            case 2:
                return "Unidad y medida";
            default:
                return "[]";
        }
    }

    /**
     * Retorna el número de elementos obtenidos de la tabla ingredientes
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return datos.size();
    }

    /**
     * Retorna el número de columnas
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    /**
     * Retorna el valor que contenga la intersección de una fila y la columna
     * pasada como parámetro
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ingrediente preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getIdIngrediente();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getUnidad_medida();
            default:
                return "";
        }
    }

    /**
     * Muestra una lista de la tabla de ingredientes basados la preparación de
     * un platillo
     *
     * @param datos
     * @throws DAOException
     */
    public void updateModel(int idPlatillo) throws DAOException {
        this.datos = platilloIngredeinte.obtenerIngredientesPorPlatillo(idPlatillo);
    }

    /**
     * Limpia la tabla de ingredientes y notifica a la vista.
     */
    public void limpiarDatos() {
        datos.clear(); // Limpia la lista de ingredientes
        fireTableDataChanged(); // Notifica a la tabla que los datos cambiaron
    }

}
