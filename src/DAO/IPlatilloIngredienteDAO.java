/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.Ingrediente;
import Modelo.Platillo;
import Modelo.PlatilloIngrediente;
import java.util.List;

/**
 *
 * @author Wyrnm
 */
public interface IPlatilloIngredienteDAO extends IDAO<PlatilloIngrediente, Integer> {

    // Obtener los ingredientes de un platillo dado su ID
    List<Ingrediente> obtenerIngredientesPorPlatillo(int idPlatillo) throws DAOException;

    // Obtener los platillos en los que se utiliza un ingrediente
    List<Platillo> obtenerPlatillosPorIngrediente(int idIngrediente) throws DAOException;

    // Eliminar la relación platillo-ingrediente específica
    void eliminar(PlatilloIngrediente pi) throws DAOException;
}
