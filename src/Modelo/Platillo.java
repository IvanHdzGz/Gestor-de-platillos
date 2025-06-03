/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.math.BigDecimal;

/**
 *
 * @author Wyrnm
 */
public class Platillo {

    // Campos o propiedades
    private int idPlatillo;
    private String nombre;
    private String tipo;
    private BigDecimal precio;

    // Declaración de costructor vacío
    public Platillo() {
        super();
    }

    // Declaración del constructor con tres parámetros
    public Platillo(String nombre, String tipo, BigDecimal precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    // Declaración del constructor con cuatro parámetros
    public Platillo(int idPlatillo, String nombre, String tipo, BigDecimal precio) {
        this.idPlatillo = idPlatillo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    // Declaración de métodos de acceso
    public int getIdPlatillo() {
        return idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    // Declaración de métodos mutadores
    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
