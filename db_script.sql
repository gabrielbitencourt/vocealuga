DROP DATABASE IF EXISTS `vocealuga`;
CREATE DATABASE `vocealuga`;

USE `vocealuga`;

CREATE TABLE `clientes` (
	`cpf` VARCHAR(11) NOT NULL,
	`nome` VARCHAR(255) NOT NULL,
	`sobrenome` VARCHAR(255) NOT NULL,
	`celular` VARCHAR(11) NOT NULL UNIQUE,
	`endereco` VARCHAR(255) NOT NULL,
	`nascimento` DATE NOT NULL,
	`email` VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY (`cpf`)
);

CREATE TABLE `funcionarios` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	`sobrenome` VARCHAR(255) NOT NULL,
	`usuario` VARCHAR(255) NOT NULL UNIQUE,
	`senha` VARCHAR(512) NOT NULL,
	`gerente` TINYINT(1) NOT NULL DEFAULT 0,
	`filial_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `grupos` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(21) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `marcas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `modelos` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	`ano` YEAR,
	`grupo_id` INT,
	`marca_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `veiculos` (
	`placa` VARCHAR(7) NOT NULL,
	`disponivel` TINYINT(1) NOT NULL DEFAULT 1,
	`km` INT NOT NULL DEFAULT 0,
	`comprado` DATE NOT NULL,
	`filial_id` INT NOT NULL,
	`modelo_id` INT NOT NULL,
	PRIMARY KEY (`placa`)
);

CREATE TABLE `reservas` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`retirada` DATE NOT NULL,
	`entrega` DATE NOT NULL,
	`filial_id` INT NOT NULL,
	`cliente_id` VARCHAR(11) NOT NULL,
	`grupo_id` INT NOT NULL,
	`veiculo_id` VARCHAR(7),
	`pontos` INT NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
);

CREATE TABLE `filiais` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `manutencoes` (
	`veiculo_id` VARCHAR(7) NOT NULL,
	`data` DATE NOT NULL
);

CREATE TABLE `motoristas` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`cliente_id` VARCHAR(11),
	`nome_completo` VARCHAR(255),
	`nascimento` DATE,
	`cnh` VARCHAR(11) NOT NULL,
	`reserva_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `incidentes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`descricao` TEXT NOT NULL,
	`motorista_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `cartoes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`titular` TEXT NOT NULL,
	`numero` VARCHAR(20) NOT NULL UNIQUE,
	`codigo` VARCHAR(3) NOT NULL,
	`expiracao` DATE NOT NULL,
	`cliente_id` VARCHAR(11) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `pagamentos` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`reserva_id` INT NOT NULL,
	`cartao_id` INT,
	`valor` INT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `funcionarios` ADD CONSTRAINT `funcionarios_fk0` FOREIGN KEY (`filial_id`) REFERENCES `filiais`(`id`);

ALTER TABLE `veiculos` ADD CONSTRAINT `veiculos_fk0` FOREIGN KEY (`filial_id`) REFERENCES `filiais`(`id`);

ALTER TABLE `veiculos` ADD CONSTRAINT `veiculos_fk1` FOREIGN KEY (`modelo_id`) REFERENCES `modelos`(`id`);

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk1` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk2` FOREIGN KEY (`grupo_id`) REFERENCES `grupos`(`id`);

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk3` FOREIGN KEY (`filial_id`) REFERENCES `filiais`(`id`);

ALTER TABLE `manutencoes` ADD CONSTRAINT `manutencoes_fk0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk1` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `incidentes` ADD CONSTRAINT `incidentes_fk0` FOREIGN KEY (`motorista_id`) REFERENCES `motoristas`(`id`);

ALTER TABLE `cartoes` ADD CONSTRAINT `cartoes_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk0` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk1` FOREIGN KEY (`cartao_id`) REFERENCES `cartoes`(`id`);

INSERT INTO `filiais` (`nome`) VALUES
('Rio de Janeiro');

INSERT INTO `funcionarios` (`nome`, `sobrenome`, `usuario`, `senha`, `filial_id`) VALUES
('Funcionário', 'da Silva', 'func', '$2a$10$ACqwDzSgoVzbsbGGO605PO7MqFav.BaCUQW8ToEWm1BD0nC3fSbN2', 1);

INSERT INTO `grupos` (`nome`) VALUES
('Grupo A'),
('Grupo B'),
('Grupo C');

INSERT INTO `marcas` (`nome`) VALUES
('Audi'),
('BMW'),
('Citroën'),
('Ferrari'),
('Fiat'),
('Ford'),
('GM - Chevrolet'),
('Honda'),
('Hyundai'),
('Jeep'),
('LAMBORGHINI'),
('Land Rover'),
('Mercedes-Benz'),
('Mitsubishi'),
('Nissan'),
('Peugeot'),
('Porsche'),
('Suzuki'),
('Toyota'),
('VW - VolksWagen');
-- OUTRAS MARCAS
-- ('Acura'),
-- ('Agrale'),
-- ('Alfa Romeo'),
-- ('AM Gen'),
-- ('Asia Motors'),
-- ('ASTON MARTIN'),
-- ('Baby'),
-- ('BRM'),
-- ('Bugre'),
-- ('Cadillac'),
-- ('CBT Jipe'),
-- ('CHANA'),
-- ('CHANGAN'),
-- ('CHERY'),
-- ('Chrysler'),
-- ('Cross Lander'),
-- ('Daewoo'),
-- ('Daihatsu'),
-- ('Dodge'),
-- ('EFFA'),
-- ('Engesa'),
-- ('Envemo'),
-- ('Fibravan'),
-- ('FOTON'),
-- ('Fyber'),
-- ('GEELY'),
-- ('GREAT WALL'),
-- ('Gurgel'),
-- ('HAFEI'),
-- ('Isuzu'),
-- ('IVECO'),
-- ('JAC'),
-- ('Jaguar'),
-- ('JINBEI'),
-- ('JPX'),
-- ('Kia Motors'),
-- ('Lada'),
-- ('Lexus'),
-- ('LIFAN'),
-- ('LOBINI'),
-- ('Lotus'),
-- ('Mahindra'),
-- ('Maserati'),
-- ('Matra'),
-- ('Mazda'),
-- ('Mclaren'),
-- ('Mercury'),
-- ('MG'),
-- ('MINI'),
-- ('Miura'),
-- ('Plymouth'),
-- ('Pontiac'),
-- ('RAM'),
-- ('RELY'),
-- ('Renault'),
-- ('Rolls-Royce'),
-- ('Rover'),
-- ('Saab'),
-- ('Saturn'),
-- ('Seat'),
-- ('SHINERAY'),
-- ('smart'),
-- ('SSANGYONG'),
-- ('Subaru'),
-- ('TAC'),
-- ('Troller'),
-- ('Volvo'),
-- ('Wake'),
-- ('Walk');


-- SEEDERS
INSERT INTO `modelos` (`nome`, `ano`, `grupo_id`, `marca_id`) VALUES
('Carro Teste', NULL, 1, 1);

INSERT INTO `clientes` (`cpf`, `nome`, `sobrenome`, `celular`, `endereco`, `nascimento`, `email`) VALUES
('11111111111', 'Cliente', 'da Silva', '21999999999', 'Rua dos Bobos, 0', '2001-01-01', 'cliente@vcaluga.com');

INSERT INTO `veiculos` (`placa`, `disponivel`, `km`, `comprado`, `filial_id`, `modelo_id`)
VALUES ('QWE5710', '1', '0', '2019-04-25', '1', '1')
