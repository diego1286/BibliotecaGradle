create table prestamos(
    id int not null auto_increment,
    isbn varchar(10) not null,
    identification_usuario varchar(10) not null,
    tipo_usuario INT(1) not null,
    fecha_maxima_devolucion varchar(20) not null,
    primary key(id)
);