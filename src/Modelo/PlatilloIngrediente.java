/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Wyrnm
 */
public class PlatilloIngrediente {

    // Campos de la tabla intermedia (clave foránea)
    private int idPlatillo;
    private int idIngrediente;

    // Constructor sin parámetros
    public PlatilloIngrediente() {
    }

    // Constructor con dos parámetros definiendo primero el idPlatillo
    public PlatilloIngrediente(int idPlatillo, int idIngrediente) {
        this.idPlatillo = idPlatillo;
        this.idIngrediente = idIngrediente;
    }

    // Descriptores de acceso
    public int getIdPlatillo() {
        return idPlatillo;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

}
