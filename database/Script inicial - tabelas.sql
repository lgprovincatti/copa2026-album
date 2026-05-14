--Script de Criação de Tabela
CREATE TABLE selecao (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(10) NOT NULL UNIQUE,
    grupo_copa VARCHAR(5),
    ativa CHAR(1) DEFAULT 'S'
);

CREATE TABLE categoria_figurinha (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE figurinha (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    numero INTEGER,
    descricao VARCHAR(200),
    categoria_id BIGINT NOT NULL,
    selecao_id BIGINT,
    rara CHAR(1) DEFAULT 'N',
    observacao VARCHAR(300),
    CONSTRAINT fk_figurinha_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria_figurinha(id),
    CONSTRAINT fk_figurinha_selecao
        FOREIGN KEY (selecao_id)
        REFERENCES selecao(id)
);

CREATE TABLE album (
    figurinha_id BIGINT PRIMARY KEY,
    possui CHAR(1) DEFAULT 'N',
    repetidas INTEGER DEFAULT 0,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_album_figurinha
        FOREIGN KEY (figurinha_id)
        REFERENCES figurinha(id)
);

--Índices
CREATE INDEX idx_figurinha_selecao
ON figurinha(selecao_id);

CREATE INDEX idx_figurinha_categoria
ON figurinha(categoria_id);

CREATE INDEX idx_selecao_grupo
ON selecao(grupo_copa);

--Insert Inicial
INSERT INTO categoria_figurinha (nome)
VALUES ('FWC');

INSERT INTO categoria_figurinha (nome)
VALUES ('SELECAO');

INSERT INTO categoria_figurinha (nome)
VALUES ('COCA_COLA');

INSERT INTO categoria_figurinha (nome)
VALUES ('ESPECIAL');