    drop table if exists student;
    create table student (
        dob date,
        id uuid not null,
        name varchar(50) not null,
        city varchar(255),
        email varchar(255),
        gender varchar(255),
        house_no varchar(255),
        parent_name varchar(255),
        password varchar(255),
        phone_no varchar(255),
        street_name varchar(255),
        zipcode varchar(255),
        primary key (id)
    );