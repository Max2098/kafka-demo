CREATE TABLE kafka.outbox_event
(
    key        varchar     primary key,
    topic      varchar     NOT NULL,
    payload    text        NOT NULL,
    created_at timestamptz NOT NULL,
    sent       boolean     DEFAULT FALSE
);