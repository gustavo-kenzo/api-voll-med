CREATE TABLE consultas (
    id BIGINT NOT NULL AUTO_INCREMENT,

    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,

    data_hora DATETIME NOT NULL,

    status tinyint not null,

    PRIMARY KEY (id),

    CONSTRAINT fk_consultas_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes (id),

    CONSTRAINT fk_consultas_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos (id),

    -- evita dois agendamentos no mesmo horário para o mesmo médico
    CONSTRAINT uk_medico_data UNIQUE (medico_id, data_hora)
);