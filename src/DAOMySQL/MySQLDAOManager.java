/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOManager;
import DAO.IIngredienteDAO;
import DAO.IPlatilloDAO;
import DAO.IPlatilloIngredienteDAO;

/**
 *
 * @author Wyrnm
 */
public class MySQLDAOManager implements DAOManager {

    private IPlatilloDAO platillos = null;
    private IIngredienteDAO ingredientes = null;
    private IPlatilloIngredienteDAO platillosIngredientes = null;

    // Con esta clase haremos el uso del patrón singleton para reusar objetos si estos ya han sido creados
    @Override
    public IPlatilloDAO getPlatilloDAO() {
        if (platillos == null) {
            platillos = new MySQLPlatilloDAO();
        }
        return platillos;
    }

    @Override
    public IIngredienteDAO getIngredienteDAO() {
        if (ingredientes == null) {
            ingredientes = new MySQLIngredienteDAO();
        }
        return ingredientes;
    }

    @Override
    public IPlatilloIngredienteDAO getPlatilloIngredienteDAO() {
        if (platillosIngredientes == null) {
            platillosIngredientes = new MySQLPlatilloIngredienteDAO();
        }
        return platillosIngredientes;
    }
}
