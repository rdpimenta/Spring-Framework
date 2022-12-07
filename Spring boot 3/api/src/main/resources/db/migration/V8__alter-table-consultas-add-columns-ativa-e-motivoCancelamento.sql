alter table consultas add ativa tinyint;
update consultas set ativa = 1;

alter table consultas add motivo_cancelamento varchar(30);