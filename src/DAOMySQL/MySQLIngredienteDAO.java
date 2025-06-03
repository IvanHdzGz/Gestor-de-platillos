/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import DAO.IIngredienteDAO;
import Modelo.Ingrediente;
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
public class MySQLIngredienteDAO implements IIngredienteDAO {

    // Propiedades para manipular la base de datos
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    // COnsultas SQL a utilizar
    private final String INSERT = "INSERT INTO ingredientes (idingrediente, nombre, unidad_medida) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE ingredientes SET nombre = ?, unidad_medida = ? WHERE idingrediente = ?";
    private final String DELETE = "DELETE FROM ingredientes WHERE idingrediente = ?";
    private final String GETALL = "SELECT * FROM ingredientes";
    private final String GETONE = "SELECT * FROM ingredientes WHERE idingrediente = ?";

    /**
     * @param ps
     * @param rs
     * @param conn
     * @throws DAOException
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
            throw new DAOException("Error en SQL" + ex);
        }
    }

    /**
     * @param i
     * @throws DAOException
     */
    @Override
    public void insertar(Ingrediente i) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Prepara la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getUnidad_medida());

            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo guardar el nuevo ingrediente.");
            } else {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    i.setIdIngrediente(rs.getInt(1));
                } else {
                    throw new DAOException("No se pudo asignar el ID a este ingrediente.");
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
    }// Fin del método insertar

    /**
     * @param i
     * @throws DAOException
     */
    @Override
    public void modificar(Ingrediente i) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Prepara la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getUnidad_medida());
            ps.setInt(3, i.getIdIngrediente());
            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Hubo un problema y no se guardaron los cambios");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
    }

    /**
     * @param id
     * @throws DAOException
     */
    @Override
    public void eliminar(Integer id) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Prepara la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);

            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Hubo un problema y se no pudo eliminar el registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
    }

    /**
     * @return @throws DAOException
     */
    @Override
    public List<Ingrediente> obtenerTodos() throws DAOException {
        // Lista de autores a retornar
        List<Ingrediente> misIngredientes = new ArrayList<Ingrediente>();

        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // preparamos la consulta
            ps = conn.prepareStatement(GETALL);

            // Ejecutamos la consulta y almacenamos el resultado en un objeto ResultSet
            rs = ps.executeQuery();

            // Recorremos el ResultSet y agregamos cada item al arraylist
            while (rs.next()) {
                Ingrediente miIngrediente = new Ingrediente();
                miIngrediente.setIdIngrediente(rs.getInt("idPlatillo"));
                miIngrediente.setNombre(rs.getString("nombre"));
                miIngrediente.setUnidad_medida(rs.getString("unidad_medida"));

            }// Fin del while
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
        return misIngredientes;
    }

    /**
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Ingrediente obtener(Integer id) throws DAOException {
        // Lista de ingredientes a retornar
        Ingrediente miIngrediente = null;

        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Preparamos la consulta y definimos sus parámetros que recibe la consulta
            ps = conn.prepareStatement(GETONE);
            ps.setInt(1, id);

            // Ejecutamos la consuta y almacenamos el resutado en un objeto ResultSet
            rs = ps.executeQuery();

            // Verificamos si el ResultSet obtuvo u nresultado y lo asignamos al correspondiente
            if (rs.next()) {
                miIngrediente = new Ingrediente();
                miIngrediente.setIdIngrediente(rs.getInt("idIngrediente"));
                miIngrediente.setNombre(rs.getString("nombre"));
                miIngrediente.setUnidad_medida(rs.getString("unidad_medida"));
            } else {
                throw new DAOException("No se encontro el elemento");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally

        return miIngrediente;
    }

}
