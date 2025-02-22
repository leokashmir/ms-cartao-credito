CREATE DATABASE IF NOT EXISTS dbcartoes
    CHARACTER
SET utf8mb4
    COLLATE utf8mb4_general_ci;

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

-- Tabela Conta
CREATE TABLE Conta
(
    id_conta     INT AUTO_INCREMENT PRIMARY KEY,
    numero_conta VARCHAR(50) UNIQUE NOT NULL,
    id_cliente   INT                NOT NULL,
    ativo        BOOLEAN   DEFAULT TRUE,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente)
);

-- Tabela Cartao
CREATE TABLE Cartao
(
    id_cartao        INT AUTO_INCREMENT PRIMARY KEY,
    num_cartao       VARCHAR(16) UNIQUE NOT NULL,
    titular          VARCHAR(20)        NOT NULL,
    cvv              VARCHAR(4)         NOT NULL,
    dt_validade      DATE               NOT NULL,
    tp_cartao        VARCHAR(7)         NOT NULL,
    dt_expiracao_cvv DATE               NOT NULL,
    id_conta         INT                NOT NULL,
    ativo            BOOLEAN   DEFAULT FALSE,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE Tracking
(
    tracking_id            VARCHAR(255) PRIMARY KEY,
    delivery_status        VARCHAR(50)  NOT NULL,
    delivery_date          DATETIME     NOT NULL,
    delivery_return_reason TEXT DEFAULT NULL,
    delivery_address       VARCHAR(255) NOT NULL,
    id_Cartao               INT          NOT NULL,
    FOREIGN KEY (id_Cartao) REFERENCES Cartao (id_Cartao)
);

-- -- Atualizar a tabela Conta para adicionar a chave estrangeira para Cartao
-- ALTER TABLE Conta
--     ADD COLUMN id_cartao INT;
-- ALTER TABLE Conta
--     ADD CONSTRAINT fk_cartao FOREIGN KEY (id_cartao) REFERENCES Cartao (id_cartao);
