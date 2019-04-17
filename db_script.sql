DROP DATABASE IF EXISTS `vocealuga`;
CREATE DATABASE `vocealuga`;

CREATE TABLE `clientes` (
	`cpf` VARCHAR(11) NOT NULL,
	`nome` TEXT NOT NULL,
	`sobrenome` TEXT NOT NULL,
	`celular` TEXT NOT NULL,
	`endereco` TEXT NOT NULL,
	`cnh` VARCHAR(11) NOT NULL,
	`nascimento` DATE NOT NULL,
	`email` VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY (`cpf`)
);

CREATE TABLE `funcionarios` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` TEXT NOT NULL,
	`sobrenome` TEXT NOT NULL,
	`usuario` VARCHAR(255) NOT NULL UNIQUE,
	`senha` TEXT NOT NULL,
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
	`nome` TEXT NOT NULL,
	`ano` YEAR,
	`grupo_id` INT,
	`marca_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `veiculos` (
	`placa` VARCHAR(7) NOT NULL,
	`disponivel` bool NOT NULL DEFAULT 1,
	`km` INT NOT NULL DEFAULT '0',
	`comprado` DATE NOT NULL,
	`filial_id` INT NOT NULL,
	`modelo_id` INT NOT NULL,
	PRIMARY KEY (`placa`)
);

CREATE TABLE `reservas` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`veiculo_id` VARCHAR(7) NOT NULL,
	`inicio` DATE NOT NULL,
	`fim` DATE NOT NULL,
	`pontos` INT NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `filiais` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `manutencoes` (
	`veiculo_id` VARCHAR(7) NOT NULL,
	`data` DATE NOT NULL
);

CREATE TABLE `motoristas` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`cliente_id` VARCHAR(11) NOT NULL,
	`reserva_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `incidentes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`descricao` longtext NOT NULL,
	`motorista_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `cartoes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`numero` TEXT NOT NULL,
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

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `manutencoes` ADD CONSTRAINT `manutencoes_fk0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk1` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `incidentes` ADD CONSTRAINT `incidentes_fk0` FOREIGN KEY (`motorista_id`) REFERENCES `motoristas`(`id`);

ALTER TABLE `cartoes` ADD CONSTRAINT `cartoes_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk0` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk1` FOREIGN KEY (`cartao_id`) REFERENCES `cartoes`(`id`);

INSERT INTO `filiais` (`nome`) VALUES ('Rio de Janeiro');

INSERT INTO `funcionarios` (`nome`, `sobrenome`, `usuario`, `senha`, `filial_id`) VALUES
('Funcionário', 'da Silva', 'func', '$2a$10$ACqwDzSgoVzbsbGGO605PO7MqFav.BaCUQW8ToEWm1BD0nC3fSbN2', 1);

INSERT INTO `marcas` (`id`, `nome`) VALUES
/*
(1, 'Acura'),
(2, 'Agrale'),
(3, 'Alfa Romeo'),
(4, 'AM Gen'),
(5, 'Asia Motors'),
(189, 'ASTON MARTIN'),
(207, 'Baby'),
(8, 'BRM'),
(123, 'Bugre'),
(10, 'Cadillac'),
(11, 'CBT Jipe'),
(136, 'CHANA'),
(182, 'CHANGAN'),
(161, 'CHERY'),
(12, 'Chrysler'),
(14, 'Cross Lander'),
(15, 'Daewoo'),
(16, 'Daihatsu'),
(17, 'Dodge'),
(147, 'EFFA'),
(18, 'Engesa'),
(19, 'Envemo'),
(149, 'Fibravan'),
(190, 'FOTON'),
(170, 'Fyber'),
(199, 'GEELY'),
(153, 'GREAT WALL'),
(24, 'Gurgel'),
(152, 'HAFEI'),
(27, 'Isuzu'),
(208, 'IVECO'),
(177, 'JAC'),
(28, 'Jaguar'),
(154, 'JINBEI'),
(30, 'JPX'),
(31, 'Kia Motors'),
(32, 'Lada'),
(34, 'Lexus'),
(168, 'LIFAN'),
(127, 'LOBINI'),
(35, 'Lotus'),
(140, 'Mahindra'),
(36, 'Maserati'),
(37, 'Matra'),
(38, 'Mazda'),
(211, 'Mclaren'),
(40, 'Mercury'),
(167, 'MG'),
(156, 'MINI'),
(42, 'Miura'),
(45, 'Plymouth'),
(46, 'Pontiac'),
(185, 'RAM'),
(186, 'RELY'),
(48, 'Renault'),
(195, 'Rolls-Royce'),
(50, 'Saab'),
(51, 'Saturn'),
(52, 'Seat'),
(183, 'SHINERAY'),
(157, 'smart'),
(125, 'SSANGYONG'),
(54, 'Subaru'),
(165, 'TAC'),
(57, 'Troller'),
(58, 'Volvo'),
(163, 'Wake'),
(120, 'Walk');
*/

(6, 'Audi'),
(7, 'BMW'),
(13, 'Citroën'),
(20, 'Ferrari'),
(21, 'Fiat'),
(22, 'Ford'),
(23, 'GM - Chevrolet'),
(25, 'Honda'),
(26, 'Hyundai'),
(29, 'Jeep'),
(171, 'LAMBORGHINI'),
(33, 'Land Rover'),
(39, 'Mercedes-Benz'),
(41, 'Mitsubishi'),
(43, 'Nissan'),
(44, 'Peugeot'),
(47, 'Porsche'),
(49, 'Rover'),
(55, 'Suzuki'),
(56, 'Toyota'),
(59, 'VW - VolksWagen');
