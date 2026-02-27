-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-06-2025 a las 17:19:42
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
-- Base de datos: `cleansa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id_administrador` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fk_categoria_administrasdor` int(11) NOT NULL,
  `contrasena` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id_administrador`, `nombre`, `apellido`, `fk_categoria_administrasdor`, `contrasena`) VALUES
(4, 'admin1', 'admin1', 1, '2'),
(5, 'admin2', 'admin2', 2, '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id_carrito` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `codigoenvio` int(11) DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`id_carrito`, `fecha`, `estado`, `total`, `codigoenvio`, `fk_cliente`) VALUES
(3, '2025-06-13', 'cancelado', 0, 311, 5),
(4, '2025-06-13', 'pagado', 36000, 344, 6),
(5, '2025-06-14', 'pagado', 11000, 568, 7),
(6, '2025-06-15', 'pagado', 12000, 1072, 8),
(7, '2025-06-15', 'pagado', 5000, 185, 6),
(8, '2025-06-18', 'en proceso', 4000, 848, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito_detalle`
--

CREATE TABLE `carrito_detalle` (
  `id_carrito_detalle` int(11) NOT NULL,
  `fk_carrito` int(11) NOT NULL,
  `fk_producto` int(11) NOT NULL,
  `total_producto` double NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `carrito_detalle`
--

INSERT INTO `carrito_detalle` (`id_carrito_detalle`, `fk_carrito`, `fk_producto`, `total_producto`, `cantidad`) VALUES
(4, 3, 1, 80, 4),
(5, 4, 1, 16000, 8),
(6, 5, 2, 6000, 3),
(7, 5, 1, 6000, 3),
(8, 6, 1, 12000, 6),
(9, 7, 2, 5000, 2),
(10, 8, 1, 4000, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(1, 'Higiene personal'),
(2, 'Higiene facial'),
(3, 'Higiene corporal'),
(4, 'Higiene capilar'),
(5, 'Higiene del hogar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_administrasdor`
--

CREATE TABLE `categoria_administrasdor` (
  `id_categoria_administrasdor` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria_administrasdor`
--

INSERT INTO `categoria_administrasdor` (`id_categoria_administrasdor`, `nombre`) VALUES
(1, 'ventas'),
(2, 'envios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_usuarios`
--

CREATE TABLE `categoria_usuarios` (
  `id_categoria_usuarios` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria_usuarios`
--

INSERT INTO `categoria_usuarios` (`id_categoria_usuarios`, `nombre`) VALUES
(1, 'personal'),
(2, 'empresa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `fk_categoria_usuarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `direccion`, `dni`, `contrasena`, `fk_categoria_usuarios`) VALUES
(5, 'Flor', NULL, 'Cespedes 3445', 54325432, 'Ioruwq02!', 2),
(6, 'Gerto', NULL, 'ubhuvytvy', 65436543, 'Ioruwq02!', 2),
(7, 'Paula', NULL, 'vfcytfuyftufv', 76547654, 'Ioruwq02!', 2),
(8, 'Gero', NULL, 'bihbhvu', 12341234, 'Ioruwq02!', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `id_envio` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  `fecha_entrega` date NOT NULL,
  `direccion` varchar(90) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fk_categoria` int(11) NOT NULL,
  `peligroso` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `precio`, `stock`, `fk_categoria`, `peligroso`) VALUES
(1, 'lavandina', 2000, 1965, 5, 0),
(2, 'desodorantes', 2500, 6, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id_administrador`),
  ADD KEY `fk_administrador_categoria_administrasdor1_idx` (`fk_categoria_administrasdor`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD KEY `fk_producto_has_cliente_cliente1_idx` (`fk_cliente`);

--
-- Indices de la tabla `carrito_detalle`
--
ALTER TABLE `carrito_detalle`
  ADD PRIMARY KEY (`id_carrito_detalle`),
  ADD KEY `fk_carrito_has_producto_producto1_idx` (`fk_producto`),
  ADD KEY `fk_carrito_has_producto_carrito1_idx` (`fk_carrito`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `categoria_administrasdor`
--
ALTER TABLE `categoria_administrasdor`
  ADD PRIMARY KEY (`id_categoria_administrasdor`);

--
-- Indices de la tabla `categoria_usuarios`
--
ALTER TABLE `categoria_usuarios`
  ADD PRIMARY KEY (`id_categoria_usuarios`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `fk_cliente_categoria_usuarios_idx` (`fk_categoria_usuarios`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`id_envio`),
  ADD KEY `fk_camion_Pedidos1_idx` (`fk_pedido`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `fk_producto_Categoria1_idx` (`fk_categoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id_administrador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id_carrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `carrito_detalle`
--
ALTER TABLE `carrito_detalle`
  MODIFY `id_carrito_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `categoria_administrasdor`
--
ALTER TABLE `categoria_administrasdor`
  MODIFY `id_categoria_administrasdor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `categoria_usuarios`
--
ALTER TABLE `categoria_usuarios`
  MODIFY `id_categoria_usuarios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `id_envio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `fk_administrador_categoria_administrasdor1` FOREIGN KEY (`fk_categoria_administrasdor`) REFERENCES `categoria_administrasdor` (`id_categoria_administrasdor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `fk_producto_has_cliente_cliente1` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `carrito_detalle`
--
ALTER TABLE `carrito_detalle`
  ADD CONSTRAINT `fk_carrito_has_producto_carrito1` FOREIGN KEY (`fk_carrito`) REFERENCES `carrito` (`id_carrito`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_carrito_has_producto_producto1` FOREIGN KEY (`fk_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_categoria_usuarios` FOREIGN KEY (`fk_categoria_usuarios`) REFERENCES `categoria_usuarios` (`id_categoria_usuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `fk_camion_Pedidos1` FOREIGN KEY (`fk_pedido`) REFERENCES `carrito_detalle` (`id_carrito_detalle`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_Categoria1` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE cupones (
  id_cupon INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(50) NOT NULL UNIQUE,
  tipo TINYINT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  alcance TINYINT NOT NULL,
  id_categoria INT NULL,
  id_producto INT NULL,
  activo TINYINT NOT NULL DEFAULT 1,f
  fecha_desde DATETIME NULL,
  fecha_hasta DATETIME NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

  -- Validaciones básicas (MySQL 8+ las evalúa; en versiones viejas puede ignorarlas)
  CONSTRAINT chk_tipo_cupon CHECK (tipo IN (1,2)),
  CONSTRAINT chk_alcance_cupon CHECK (alcance IN (1,2,3)),
  CONSTRAINT chk_valor_positivo CHECK (valor > 0),
  CONSTRAINT chk_fechas CHECK (fecha_hasta IS NULL OR fecha_desde IS NULL OR fecha_hasta >= fecha_desde),
  -- Regla de consistencia del objetivo según alcance
  CONSTRAINT chk_objetivo_cupon CHECK (
    (alcance = 1 AND id_categoria IS NULL AND id_producto IS NULL) OR
    (alcance = 2 AND id_categoria IS NOT NULL AND id_producto IS NULL) OR
    (alcance = 3 AND id_producto IS NOT NULL AND id_categoria IS NULL)
  ),
  CONSTRAINT fk_cupon_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria),
  CONSTRAINT fk_cupon_producto  FOREIGN KEY (id_producto)  REFERENCES productos(id_producto)
);
CREATE INDEX idx_cupon_codigo ON cupones(codigo);
CREATE INDEX idx_cupon_activo ON cupones(activo);
CREATE INDEX idx_cupon_categoria ON cupones(id_categoria);
CREATE INDEX idx_cupon_producto ON cupones(id_producto);


INSERT INTO cupones (
  codigo, tipo, valor, alcance,
  id_categoria, id_producto,
  activo, fecha_desde, fecha_hasta
) VALUES (
  'WELCOME10',
  1,          -- porcentaje
  10,         -- 10%
  1,          -- carrito
  NULL,
  NULL,
  1,
  NOW(),
  NULL
);

INSERT INTO cupones (
  codigo, tipo, valor, alcance,
  id_categoria, id_producto,
  activo, fecha_desde, fecha_hasta
) VALUES (
  'FACIAL15',
  1,          -- porcentaje
  15,         -- 15%
  2,          -- categoria
  2,          -- Higiene facial
  NULL,
  1,
  NOW(),
  '2026-12-31'
);

INSERT INTO cupones (
  codigo, tipo, valor, alcance,
  id_categoria, id_producto,
  activo
) VALUES (
  'HOGAR1000',
  2,          -- monto fijo
  1000,       -- $1000
  2,          -- categoria
  5,          -- Higiene del hogar
  NULL,
  1
);