-- Resetar os contadores das sequências para zero
ALTER SEQUENCE pauta_id_seq RESTART WITH 1;
ALTER SEQUENCE sessao_votacao_id_seq RESTART WITH 1;
ALTER SEQUENCE voto_id_seq RESTART WITH 1;

-- Apagar os dados existentes
DELETE FROM voto;
DELETE FROM sessao_votacao;
DELETE FROM pauta;

-- Inserir dados na tabela pauta
INSERT INTO pauta (descricao)
VALUES ('Pauta 1'),
       ('Pauta 2'),
       ('Pauta 3');

-- Inserir dados na tabela sessao_votacao
INSERT INTO sessao_votacao (pauta_id, fechada, data_abertura, data_fechamento_real, data_fechamento)
VALUES (1, false, NOW(), NULL, NOW() + INTERVAL '5 minutes'),
       (2, false, NOW(), NULL, NOW() + INTERVAL '5 minutes'),
       (3, false, NOW(), NULL, NOW() + INTERVAL '5 minutes');

-- Inserir dados na tabela voto
INSERT INTO voto (sessao_votacao_id, associado_id, voto)
VALUES (1, 1, true),
       (1, 2, false),
       (2, 3, true),
       (2, 4, true),
       (3, 5, false),
       (3, 6, true);
