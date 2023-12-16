drop table user;
create table user(
    id integer not null primary key AUTO_INCREMENT,
    login varchar(125) not null,
    position varchar(125) not null,
    password varchar(125) not null,
    role varchar(125) not null,
    permissions varchar(125) not null
);
insert into user(login, position, password, role, permissions) values('user', 'position1', 'password', 'USER', 'WEATHER_GET');
insert into user(login, position, password, role, permissions) values('admin', 'position2', 'password', 'ADMIN', 'WEATHER_DELETE');