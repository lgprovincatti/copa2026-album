--Script de Dados

/* =========================================================
   FIGURINHAS FWC
========================================================= */

INSERT INTO figurinha
(codigo, numero, descricao, categoria_id, selecao_id)
SELECT
    'FWC ' || numero,
    numero,
    'FIFA WORLD CUP 2026',
    1,
    (
        SELECT id
        FROM selecao
        WHERE sigla = 'FWC'
    )
FROM generate_series(1, 19) numero;



/* =========================================================
   FIGURINHAS DAS SELEÇÕES
========================================================= */

INSERT INTO figurinha
(codigo, numero, descricao, categoria_id, selecao_id)
SELECT
    s.sigla || ' ' || gs.numero,
    gs.numero,
    s.nome,
    2,
    s.id
FROM selecao s
CROSS JOIN generate_series(1, 20) gs(numero)
WHERE s.sigla NOT IN ('FWC', 'CC', 'SPC');



/* =========================================================
   FIGURINHAS COCA-COLA
========================================================= */

INSERT INTO figurinha
(codigo, numero, descricao, categoria_id, selecao_id)
SELECT
    'CC' || numero,
    numero,
    'Coca-Cola',
    3,
    (
        SELECT id
        FROM selecao
        WHERE sigla = 'CC'
    )
FROM generate_series(1, 14) numero;



/* =========================================================
   FIGURINHA ESPECIAL 00
========================================================= */

INSERT INTO figurinha
(codigo, numero, descricao, categoria_id, selecao_id)
VALUES
(
    '00',
    0,
    'Figurinha Especial',
    4,
    (
        SELECT id
        FROM selecao
        WHERE sigla = 'SPC'
    )
);



/* =========================================================
   INICIALIZAÇÃO DO ÁLBUM
========================================================= */

INSERT INTO album
(figurinha_id, possui, repetidas)
SELECT
    id,
    'N',
    0
FROM figurinha;