CREATE DATABASE IF NOT EXISTS dbcartoes;
USE dbcartoes;

-- Tabela Cliente
CREATE TABLE Cliente
(
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(255)        NOT NULL,
    documento  VARCHAR(20) UNIQUE  NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela Endereco
CREATE TABLE Endereco
(
    id_endereco   INT AUTO_INCREMENT PRIMARY KEY,
    logradouro    VARCHAR(255) NOT NULL,
    numero        VARCHAR(10)  NOT NULL,
    complemento   VARCHAR(255),
    bairro        VARCHAR(100) NOT NULL,
    cidade        VARCHAR(100) NOT NULL,
    estado        VARCHAR(2)   NOT NULL,
    codigo_postal VARCHAR(20)  NOT NULL,
    id_cliente    INT          NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente)
);

-- Tabela Cartao
CREATE TABLE Cartao
(
    id_cartao   INT AUTO_INCREMENT PRIMARY KEY,
    num_cartao  VARCHAR(16) UNIQUE        NOT NULL,
    nome        VARCHAR(20)               NOT NULL,
    cvv         VARCHAR(4)                NOT NULL,
    dt_validade DATE                      NOT NULL,
    tp_cartao   ENUM('Fisico', 'Virtual') NOT NULL,
    ativo       BOOLEAN   DEFAULT FALSE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);


-- Tabela Conta
CREATE TABLE Conta
(
    id_conta     INT AUTO_INCREMENT PRIMARY KEY,
    numero_conta VARCHAR(50) UNIQUE NOT NULL,
    id_cliente   INT                NOT NULL,
    id_cartao    INT                NOT NULL,
    ativo        BOOLEAN   DEFAULT TRUE,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente),
    FOREIGN KEY (id_cartao) REFERENCES Cartao (id_cartao)
);

