create table usuario (
id int(11) not null auto_increment,
primer_nombre varchar(100) null,
segundo_nombre varchar(100) null,
primer_apellido varchar(100) null,
segundo_apellido varchar(100) null,
tipo_de_documento varchar(100) null,
documento varchar(100) not null,
administradora_salud varchar(100) null,
fecha_afiliacion_Salud datetime null,
administradora_de_pension varchar(100) null,
fecha_afiliacion_pension datetime null,
primary key (id)
);




