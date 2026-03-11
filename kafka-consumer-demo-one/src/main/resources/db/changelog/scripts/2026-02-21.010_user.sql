CREATE TABLE kafka.user
(
    id              bigserial primary key,
    unique_key      varchar     NOT NULL UNIQUE,
    name            varchar     NOT NULL,
    phone           varchar     NOT NULL,
    balance         varchar     NOT NULL,
    birthday        date        NOT NULL,
    create_at       timestamptz NOT NULL
);