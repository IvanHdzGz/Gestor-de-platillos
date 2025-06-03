# Proyecto Final - Platillos e Ingredientes

Aplicación Java desarrollada con Apache NetBeans y Apache Ant para la gestión de platillos y sus ingredientes. Este proyecto simula un sistema de registro y consulta de recetas, diseñado como parte de una asignatura académica de programación.

---

## Índice

- [Descripción General](#descripción-general)
- [Características](#características)
- [Requisitos](#requisitos)
- [Ejecución](#ejecución)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Autores](#autores)

---

## Descripción General

El sistema permite al usuario ingresar, visualizar y generar reportes de diferentes platillos y sus ingredientes. A través de una interfaz sencilla, se pueden almacenar datos de manera estructurada.

Además, el proyecto incluye un archivo PDF (`ReportePlatillosEIngredientes.pdf`) que describe su funcionalidad y diseño.

---

## Características

- Registro de platillos con múltiples ingredientes
- Gestión básica de datos (inserción, visualización)
- Reporte en formato PDF (manual/documentación)
- Uso de Apache Ant para la automatización de la compilación y ejecución
- Organización modular del código en NetBeans

---

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Apache NetBeans 12 o superior
- Apache Ant (incluido en NetBeans)
- Git (opcional, para manejo de versiones)

---

## Ejecución

Puedes ejecutar el proyecto desde NetBeans o desde línea de comandos usando Ant:

### Desde NetBeans

1. Abre el proyecto (`Archivo > Abrir proyecto`)
2. Haz clic derecho sobre el proyecto y selecciona `Limpiar y Construir`
3. Ejecuta con `Run > Run Project`

### Desde consola:

```bash
cd ProyectoFinal-Platillos
ant clean
ant build
ant run
```

## Estructura del proyecto

ProyectoFinal-Platillos/
├── src/                   # Código fuente
├── nbproject/             # Configuración del proyecto (NetBeans)
├── build.xml              # Script Ant para compilar y ejecutar
├── manifest.mf            # Archivo de manifiesto para ejecutar el JAR
├── ReportePlatillosEIngredientes.pdf
├── .gitignore
└── README.md

## Autores

**Iván Hernández Gómez**  
Estudiante de Ingeniería en Tecnologías computacionales
ivanhdzgz1310@gmail.com
[GitHub](https://github.com/IvanHdzGz)  
Responsable del desarrollo y documentación del proyecto
