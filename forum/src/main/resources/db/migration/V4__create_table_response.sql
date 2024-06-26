create table response (
    id BIGSERIAL not null,
    message varchar(50) not null,
    date_created timestamp not null,
    topic_id bigint not null,
    author_id bigint not null,
    solution int not null,
    primary key(id),
    foreign key(topic_id) references topic(id),
    foreign key(author_id) references usuario(id)
);

