/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Wyrnm
 * @param <T> Hace referencia a una clase del paquete Modelo
 * @param <K> El id que identifica a un objeto de la clase Modelo
 */
public interface IDAO<T, K> {

    void insertar(T o) throws DAOException;

    void modificar(T o) throws DAOException;

    void eliminar(K id) throws DAOException;

    List<T> obtenerTodos() throws DAOException;

    T obtener(K id) throws DAOException;
}
