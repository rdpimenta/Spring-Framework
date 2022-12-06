create table consultas(
    id bigint not null auto_increment,
    medico_id bigint,
    paciente_id bigint,
    primary key(id)
);

alter table consultas add foreign key (medico_id) references medicos(id);

alter table consultas add foreign key (paciente_id) references pacientes(id);