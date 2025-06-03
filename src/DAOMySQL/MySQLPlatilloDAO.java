/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import DAO.IPlatilloDAO;
import Modelo.Platillo;
import MySQLConexion.Conectar;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Wyrrnm
 */
public class MySQLPlatilloDAO implements IPlatilloDAO {

    // Propiedades para manipular la base de datos
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    // Consultas SQL a utilizar
    private final String INSERT = "INSERT INTO platillos (nombre, tipo, precio) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE platillos SET nombre = ?, tipo = ?, precio = ? WHERE idPlatillo = ?";
    private final String DELETE = "DELETE FROM platillos WHERE idPlatillo = ?";
    private final String GETALL = "SELECT * FROM platillos";
    private final String GETONE = "SELECT * FROM platillos WHERE idPlatillo = ?";

    /**
     *
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
     *
     * @param p
     * @throws DAOException
     */
    @Override
    public void insertar(Platillo p) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Prepara la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setBigDecimal(3, p.getPrecio());

            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo guardar el nuevo platillo.");
            } else {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    p.setIdPlatillo(rs.getInt(1));
                } else {
                    throw new DAOException("No se pudo asignar el ID a este platillo");
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
    }// Fin del método insertar

    /**
     *
     * @param p
     * @throws DAOException
     */
    @Override
    public void modificar(Platillo p) throws DAOException {
        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // Prepara la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setBigDecimal(3, p.getPrecio());
            ps.setInt(4, p.getIdPlatillo());
            // Ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Hubo un problema y no se guardaron los cambios");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
    }// Fin del método modificar

    /**
     *
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
    }// Fin del método eliminar

    /**
     *
     * @return @throws DAOException
     */
    @Override
    public List<Platillo> obtenerTodos() throws DAOException {
        // Lista de autores a retornar
        List<Platillo> misPlatillos = new ArrayList<Platillo>();

        try {
            // Creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            // preparamos la consulta
            ps = conn.prepareStatement(GETALL);

            // Ejecutamos la consulta y almacenamos el resultado en un objeto ResultSet
            rs = ps.executeQuery();

            // Recorremos el ResultSet y agregamos cada item al arraylist
            while (rs.next()) {
                Platillo miPlatillo = new Platillo();
                miPlatillo.setIdPlatillo(rs.getInt("idPlatillo"));
                miPlatillo.setNombre(rs.getString("nombre"));
                miPlatillo.setTipo(rs.getString("tipo"));
                miPlatillo.setPrecio(rs.getBigDecimal("precio"));
                misPlatillos.add(miPlatillo);
            }// Fin del while
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally
        return misPlatillos;
    }// Fin del método obtenerTodos

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Platillo obtener(Integer id) throws DAOException {
        // Lista de platillos a retornar
        Platillo miPlatillo = null;

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
                miPlatillo = new Platillo();
                miPlatillo.setIdPlatillo(rs.getInt("idPlatillo"));
                miPlatillo.setNombre(rs.getString("nombre"));
                miPlatillo.setTipo(rs.getString("tipo"));
                miPlatillo.setPrecio(rs.getBigDecimal("precio"));
            } else {
                throw new DAOException("No se encontro el elemento");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }// Fin del finally

        return miPlatillo;
    }// Fin del método obtener
}
