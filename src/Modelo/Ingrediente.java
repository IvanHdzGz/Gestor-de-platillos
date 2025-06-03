/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Wyrnm
 */
public class Ingrediente {

    // Campos o ingredientes
    private int idIngrediente;
    private String nombre;
    private String unidad_medida;

    // Declaración de constructor vacío
    public Ingrediente() {
    }

    // Declaración de constructor con dos parámetros
    public Ingrediente(String nombre, String unidad_medida) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
    }

    // Declaración de constructor con tres parámetros
    public Ingrediente(int idIngrediente, String nombre, String unidad_medida) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
    }

    // Declaración de métodos de acceso
    public int getIdIngrediente() {
        return idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    // Declaración de métodos mutadores
    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

}
