CREATE TABLE IF NOT EXISTS usuarios (
    id      BIGSERIAL PRIMARY KEY,
    nome    VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE,
    senha   VARCHAR(255) NOT NULL,
    cpf     VARCHAR(14)  NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS contas (
    id           BIGSERIAL PRIMARY KEY,
    numero_conta UUID          NOT NULL UNIQUE,
    usuario_id   BIGINT        NOT NULL REFERENCES usuarios(id),
    saldo        NUMERIC(19,2) NOT NULL DEFAULT 0,
    data_criacao TIMESTAMP     NOT NULL
    );

CREATE TABLE IF NOT EXISTS transacoes (
    id                BIGSERIAL PRIMARY KEY,
    conta_origem_id   BIGINT        NOT NULL REFERENCES contas(id),
    conta_destino_id  BIGINT        NOT NULL REFERENCES contas(id),
    valor             NUMERIC(19,2) NOT NULL,
    data_transacao    TIMESTAMP     NOT NULL,
    status_transacao  VARCHAR(20)   NOT NULL
    );