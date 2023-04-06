-- liquebase formatted sql
-- changeset erokhinaa:1

create table socks
(
    id Serial primary key,
    color varchar not null,
    cotton_part bigint not null,
    quantity bigint not null
);