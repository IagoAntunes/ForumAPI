create table usuario (
    id BIGSERIAL not null,
    nome varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

insert into usuario(id,nome,email) values(1,'Joao','Joao@gmail.com');