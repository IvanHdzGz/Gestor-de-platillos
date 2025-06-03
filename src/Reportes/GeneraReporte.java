/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

import MySQLConexion.Conectar;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import Vista.JFrmVentanaPrincipal;

/**
 * Clase responsable de generar y mostrar un reporte Jasper (.jasper) que
 * contiene la información de platillos e ingredientes. También exporta dicho
 * reporte a un archivo PDF.
 *
 * El reporte debe estar precompilado con iReport o JasperSoft Studio y
 * almacenado en la carpeta de recursos bajo /Reportes/.
 *
 * Requiere que la base de datos esté disponible para llenar el reporte.
 *
 * @author Wyrnm
 */
public class GeneraReporte {

    // Nombre del archivo .jasper que contiene el diseño y estructura del reporte
    private final String NOMBRE_JASPER = "Reporte - Platillos & Ingredientes.jasper";

    /**
     * Genera el reporte de platillos e ingredientes, mostrándolo al usuario y
     * exportándolo en formato PDF.
     *
     * @param parentFrame La ventana principal desde donde se lanza el reporte
     * (no se usa directamente pero puede ser útil para modales o mensajes).
     */
    public void generarReportePlatillosEIngredientes(JFrmVentanaPrincipal parentFrame) {
        Connection conexion = null;

        try {
            // Establecer conexión a la base de datos
            conexion = Conectar.realizarConexion();

            if (conexion == null) {
                System.err.println("Error: No se pudo establecer conexión con la base de datos.");
                return;
            }

            // Cargar el archivo .jasper desde los recursos del proyecto
            InputStream inputStream = getClass().getResourceAsStream("/Reportes/" + NOMBRE_JASPER);
            if (inputStream == null) {
                System.err.println("Error: El archivo .jasper no se encontró en los recursos del classpath: /Reportes/" + NOMBRE_JASPER);
                return;
            }

            // Cargar el objeto JasperReport desde el archivo .jasper
            JasperReport reporte = (JasperReport) JRLoader.loadObject(inputStream);

            // Mapa de parámetros (puede usarse para enviar filtros al reporte, como fechas o IDs)
            Map<String, Object> parametros = new HashMap<>();

            // Llenar el reporte con los datos desde la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            // Crear una ventana para visualizar el reporte
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Reporte de Platillos e Ingredientes");
            viewer.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            viewer.setAlwaysOnTop(true);
            viewer.setVisible(true);

            // Exportar el reporte a un archivo PDF
            String rutaSalidaPDF = "ReportePlatillosEIngredientes.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, rutaSalidaPDF);

            // Confirmación en consola
            System.out.println("Reporte generado exitosamente en: " + new File(rutaSalidaPDF).getAbsolutePath());

        } catch (SQLException e) {
            System.err.println("Error SQL al conectar o al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException e) {
            System.err.println("Error de JasperReports al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado en GeneraReporte: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar conexión a la base de datos
            if (conexion != null) {
                try {
                    Conectar.realizarDesconexion();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
