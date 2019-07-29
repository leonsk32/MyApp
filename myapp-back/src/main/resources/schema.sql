create table if not exists accounts (
    username varchar(30) primary key,
    email varchar(255) unique,
    authority varchar(20),
    password varchar(255),
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP
);

create table if not exists hagetaka_entries (
    id serial primary key,
    username varchar(30) references accounts(username),
    value integer,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP
);