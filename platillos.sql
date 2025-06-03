-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2025 a las 20:14:59
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `platillos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingredientes`
--

CREATE TABLE `ingredientes` (
  `idingrediente` int(11) NOT NULL COMMENT 'Identificador del ingrediente',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre del ingrediente',
  `unidad_medida` varchar(10) NOT NULL COMMENT 'Gramos, Mililitros, Piezas'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `ingredientes`
--

INSERT INTO `ingredientes` (`idingrediente`, `nombre`, `unidad_medida`) VALUES
(1, 'Carne de cerdo', '100 gramos'),
(2, 'Tortilla de maíz', '2 piezas'),
(3, 'Salsa al pastor', '30 mililit'),
(4, 'Pasta', '120 gramos'),
(5, 'Carne molida de res', '80 gramos'),
(6, 'Salsa de tomate', '50 mililit'),
(7, 'Alga nori', '1 pieza'),
(8, 'Arroz para sushi', '100 gramos'),
(9, 'Pescado crudo', '80 gramos'),
(10, 'Pan para hamburguesa', '1 pieza'),
(11, 'Carne de res', '120 gramos'),
(12, 'Lechuga', '2 piezas'),
(13, 'Crutones', '30 gramos'),
(14, 'Queso parmesano', '20 gramos'),
(15, 'Aderezo César', '25 mililit');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platillos`
--

CREATE TABLE `platillos` (
  `idplatillo` int(11) NOT NULL COMMENT 'Identificador del platillo',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre del platillo',
  `tipo` varchar(30) NOT NULL COMMENT 'Principal, Entrada, Postre',
  `precio` decimal(8,2) NOT NULL COMMENT 'Precio en moneda local'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `platillos`
--

INSERT INTO `platillos` (`idplatillo`, `nombre`, `tipo`, `precio`) VALUES
(1, 'Tacos al pastor', 'Principal', 35.00),
(2, 'Platano macho frito', 'Postre', 80.00),
(3, 'Sushi Roll', 'principal', 120.00),
(4, 'Hamburguesa clásica', 'principal', 90.00),
(5, 'Ensalada César', 'entrada', 65.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platillo_ingrediente`
--

CREATE TABLE `platillo_ingrediente` (
  `idplatillo` int(11) NOT NULL,
  `idingrediente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `platillo_ingrediente`
--

INSERT INTO `platillo_ingrediente` (`idplatillo`, `idingrediente`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 10),
(4, 11),
(4, 12),
(5, 12),
(5, 13),
(5, 14),
(5, 15);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`idingrediente`);

--
-- Indices de la tabla `platillos`
--
ALTER TABLE `platillos`
  ADD PRIMARY KEY (`idplatillo`);

--
-- Indices de la tabla `platillo_ingrediente`
--
ALTER TABLE `platillo_ingrediente`
  ADD PRIMARY KEY (`idplatillo`,`idingrediente`),
  ADD KEY `idingrediente` (`idingrediente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  MODIFY `idingrediente` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador del ingrediente', AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `platillos`
--
ALTER TABLE `platillos`
  MODIFY `idplatillo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador del platillo', AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `platillo_ingrediente`
--
ALTER TABLE `platillo_ingrediente`
  ADD CONSTRAINT `platillo_ingrediente_ibfk_1` FOREIGN KEY (`idplatillo`) REFERENCES `platillos` (`idplatillo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `platillo_ingrediente_ibfk_2` FOREIGN KEY (`idingrediente`) REFERENCES `ingredientes` (`idingrediente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
