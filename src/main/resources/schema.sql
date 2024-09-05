CREATE TABLE IF NOT EXISTS `cantitati` (
    `id_cantitate` VARCHAR(50) PRIMARY KEY,
    `cantitate` FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS `data` (
    `numar_factura` BIGINT PRIMARY KEY,
    `data` DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS `sucursale` (
    `sucursala` VARCHAR(50) NOT NULL,
    `numar_comanda` BIGINT NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS `comenzi` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `numar_factura` BIGINT NOT NULL,
    `numar_comanda` BIGINT NOT NULL,
    `cod_articol` BIGINT NOT NULL,
    `id_cantitate` VARCHAR(50) NOT NULL
);