/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author Wyrnm
 */
public interface DAOManager {

    /**
     * Mediante esta interfaz haremos una forma centralizada de pedir cualquier
     * DAO de entre muchos tipos de objetos
     */
    IPlatilloDAO getPlatilloDAO();

    IIngredienteDAO getIngredienteDAO();

    IPlatilloIngredienteDAO getPlatilloIngredienteDAO();
}
