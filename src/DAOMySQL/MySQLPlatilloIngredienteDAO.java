/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import DAO.IPlatilloIngredienteDAO;
import Modelo.Ingrediente;
import Modelo.Platillo;
import Modelo.PlatilloIngrediente;
import MySQLConexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wyrnm
 */
public class MySQLPlatilloIngredienteDAO implements IPlatilloIngredienteDAO {

    // Propiedades para el acceso a la base de datos
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Consultas SQL
    private final String INSERT = "INSERT INTO platillo_ingrediente (idPlatillo, idIngrediente) VALUES (?, ?)";
    private final String DELETE = "DELETE FROM platillo_ingrediente WHERE idPlatillo = ? AND idIngrediente = ?";
    private final String GETALLINGREDIENTSBYPLATILLO = "SELECT * FROM ingredientes "
            + "INNER JOIN platillo_ingrediente ON ingredientes.idIngrediente = platillo_ingrediente.idIngrediente "
            + "WHERE platillo_ingrediente.idPlatillo = ?";
    private final String GETALLPLATILLOSBYINGREDIENTE = "SELECT * FROM platillos "
            + "INNER JOIN platillo_ingrediente ON platillos.idPlatillo = platillo_ingrediente.idPlatillo "
            + "WHERE platillo_ingrediente.idIngrediente = ?";

    /**
     * Cierra las conexiones abiertas (PreparedStatement, ResultSet y
     * Connection).
     *
     * @param ps El PreparedStatement a cerrar
     * @param rs El ResultSet a cerrar
     * @param conn La conexión a cerrar
     * @throws DAOException Si ocurre un error al cerrar las conexiones
     */
    private void cerrarConexiones(PreparedStatement ps, ResultSet rs, Connection conn) throws DAOException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al cerrar conexiones: " + ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene todos los ingredientes relacionados con un platillo específico.
     *
     * @param idPlatillo El ID del platillo
     * @return Una lista de objetos Ingrediente que pertenecen al platillo
     * @throws DAOException Si ocurre un error en la operación de base de datos
     */
    @Override
    public List<Ingrediente> obtenerIngredientesPorPlatillo(int idPlatillo) throws DAOException {
        // Creamos la instanciación del parametro a retornar
        List<Ingrediente> ingredientes = new ArrayList<>();
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Preparamos la consulta
            ps = conn.prepareStatement(GETALLINGREDIENTSBYPLATILLO);
            ps.setInt(1, idPlatillo);

            // Ejecutamos la consulta y almacenamos el resultado en un objeto ResultSet
            rs = ps.executeQuery();

            // Recorremos el ResultSet y agregamos cada item al ArrayList
            while (rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(rs.getInt("idIngrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setUnidad_medida(rs.getString("unidad_medida"));
                ingredientes.add(ing);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener ingredientes por platillo", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return ingredientes;
    }

    /**
     * Obtiene todos los platillos que contienen un ingrediente específico.
     *
     * @param idIngrediente El ID del ingrediente
     * @return Una lista de objetos Platillo que utilizan el ingrediente
     * @throws DAOException Si ocurre un error en la operación de base de datos
     */
    @Override
    public List<Platillo> obtenerPlatillosPorIngrediente(int idIngrediente) throws DAOException {
        // Creamos la instanciación del parametro a retornar
        List<Platillo> platillos = new ArrayList<>();
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Preparamos la consulta
            ps = conn.prepareStatement(GETALLPLATILLOSBYINGREDIENTE);
            ps.setInt(1, idIngrediente);

            // Ejecutamos la consulta y almacenamos el resultado en un objeto ResultSet
            rs = ps.executeQuery();

            // Recorremos el ResultSet y agregamos cada item al ArrayList
            while (rs.next()) {
                Platillo p = new Platillo();
                p.setIdPlatillo(rs.getInt("idPlatillo"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getBigDecimal("precio"));
                platillos.add(p);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener platillos por ingrediente", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return platillos;
    }

    /**
     * Elimina una relación existente entre un platillo y un ingrediente.
     *
     * @param pi Objeto PlatilloIngrediente que contiene el ID del platillo y
     * del ingrediente a desvincular
     * @throws DAOException Si ocurre un error al ejecutar la eliminación
     */
    @Override
    public void eliminar(PlatilloIngrediente pi) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Preparamos la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, pi.getIdPlatillo());
            ps.setInt(2, pi.getIdIngrediente());

            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo eliminar la relación platillo-ingrediente");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar la relación", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Inserta una nueva relación entre un platillo y un ingrediente.
     *
     * @param o Objeto PlatilloIngrediente que contiene el ID del platillo y del
     * ingrediente a relacionar
     * @throws DAOException Si ocurre un error al ejecutar la inserción
     */
    @Override
    public void insertar(PlatilloIngrediente o) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Preparamos la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, o.getIdPlatillo());
            ps.setInt(2, o.getIdIngrediente());

            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo insertar la relación platillo-ingrediente");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al insertar la relación", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    @Override
    public void modificar(PlatilloIngrediente o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PlatilloIngrediente> obtenerTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PlatilloIngrediente obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
