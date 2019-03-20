DROP DATABASE IF EXISTS `grupo1`;
CREATE DATABASE `grupo1`;

CREATE TABLE `clientes` (
	`cpf` VARCHAR(11) NOT NULL,
	`nome` TEXT NOT NULL,
	`sobrenome` TEXT NOT NULL,
	`telefone` TEXT NOT NULL,
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

CREATE TABLE `veiculos` (
	`placa` VARCHAR(7) NOT NULL,
	`disponivel` bool NOT NULL DEFAULT 1,
	`km` INT NOT NULL DEFAULT '0',
	`comprado` DATE NOT NULL,
	`filial_id` INT NOT NULL,
	PRIMARY KEY (`placa`)
);

CREATE TABLE `reservas` (
	`id` INT NOT NULL AUTO_INCREMENT UNIQUE,
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

ALTER TABLE `reservas` ADD CONSTRAINT `reservas_fk0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `manutencoes` ADD CONSTRAINT `manutencoes_fk0` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos`(`placa`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `motoristas` ADD CONSTRAINT `motoristas_fk1` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `incidentes` ADD CONSTRAINT `incidentes_fk0` FOREIGN KEY (`motorista_id`) REFERENCES `motoristas`(`id`);

ALTER TABLE `cartoes` ADD CONSTRAINT `cartoes_fk0` FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`cpf`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk0` FOREIGN KEY (`reserva_id`) REFERENCES `reservas`(`id`);

ALTER TABLE `pagamentos` ADD CONSTRAINT `pagamentos_fk1` FOREIGN KEY (`cartao_id`) REFERENCES `cartoes`(`id`);
