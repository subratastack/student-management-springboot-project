    drop table if exists classroom;
    CREATE TABLE classroom (
    classroom_id UUID not null,
    classroom_number INTEGER,
    classroom_section CHAR(1),
    primary key(classroom_id)
);
