create article(
    id bigint not null,
    title varchar(50) not null,
    writer varchar(50) not null,
    password varchar(50) not null,
    content text not null,
    createdAt timestamp,
    updatedAt timestamp,
    primary key(id)
);