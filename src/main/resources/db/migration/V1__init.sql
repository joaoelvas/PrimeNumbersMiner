create table numbers (
    id bigserial not null,
    integer bigint not null,
    is_prime varchar(255),
    primary key (id)
);

insert into numbers(integer, is_prime)
values (0, NULL), (1, NULL), (2, NULL), (3, NULL), (4, NULL), (5, NULL), (6, NULL);



