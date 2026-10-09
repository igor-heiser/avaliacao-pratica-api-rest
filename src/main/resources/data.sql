-- 1. Inserir Setores
INSERT IGNORE INTO setores (nome, localizacao)
VALUES ('Usinagem Leve', 'Galpão A - Setor Norte'),
       ('Prensa e Estamparia', 'Galpão B - Setor Sul'),
       ('Linha de Montagem', 'Galpão C - Setor Leste');

-- 2. Inserir Motores
INSERT IGNORE INTO motores (setor_id, codigo_ativo, fabricante, modelo, potencia_kw, rpm_nominal, data_instalacao,
                     status_atual)
VALUES (1, 'MOT-USI-001', 'WEG', 'W22 Premium', 15.50, 1800, '2023-01-15', 'EM_OPERACAO'),
       (1, 'MOT-USI-002', 'Siemens', '1LE1001', 22.00, 3600, '2023-03-20', 'EM_OPERACAO'),
       (2, 'MOT-PRE-001', 'ABB', 'M3BP 160', 45.00, 1800, '2022-11-10', 'EM_MANUTENCAO');

-- 3. Inserir Alertas de Motores
INSERT IGNORE INTO alertas_motores (motor_id, data_alerta, tipo_anomalia, criticidade, descricao, resolvido)
VALUES (1, '2026-10-01 10:30:00', 'Vibração excessiva', 'ALTA', 'Sensor detectou pico de vibração no mancal dianteiro.',
        false),
       (2, '2026-10-02 14:15:00', 'Superaquecimento', 'CRITICA',
        'Temperatura da carcaça ultrapassou 95°C durante carga nominal.', false),
       (3, '2026-09-25 08:00:00', 'Queda de RPM', 'MEDIA', 'Oscilação imprevista no RPM nominal.', true);

-- 4. Inserir Histórico de Telemetria
INSERT IGNORE INTO historico_telemetria (motor_id, data_hora, temperatura_carcaca, rpm_atual, vibracao_global)
VALUES (1, '2026-10-07 08:00:00', 45.20, 1795, 2.10),
       (1, '2026-10-07 08:05:00', 48.50, 1798, 4.80),
       (2, '2026-10-07 08:00:00', 88.00, 3590, 1.50),
       (2, '2026-10-07 08:05:00', 96.30, 3585, 1.80),
       (3, '2026-10-07 08:00:00', 35.00, 0, 0.00);