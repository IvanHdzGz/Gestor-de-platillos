/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal.platillos;

import Vista.JFrmVentanaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author Wyrnm
 */
public class ProyectoFinalPlatillos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrmVentanaPrincipal miVentanaPrincipal = new JFrmVentanaPrincipal(); // 20
        miVentanaPrincipal.setTitle("Gestión de platillos e ingredientes");

        // Maximiza la ventana
        miVentanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        miVentanaPrincipal.setVisible(true);
    }

}
