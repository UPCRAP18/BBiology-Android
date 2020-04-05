create database if not exists `bbiology`;
use `bbiology`;
create table if not exists `usuario` (
	ID char(12) not null,
    Nombre char(20) not null,
    Apellido char(20) not null,
    Iniciales char(10) not null,
    Correo char(50) not null,
    Turno char(20) not null,
    Password char(50) not null
);

alter table `usuario` add constraint UPK primary key (ID);

create table if not exists `app` (
	ID int auto_increment not null,
    ID_Prof char(12) not null,
	Asist_Aux bool default 0,
    Asist_Chico_Serv bool default 0,
    Practica_Realizar int,
    Hubo_Practica bool default 0,
    Muestras_Fija bool default 0,
    Bata bool default 0,
    Materiales_Completos bool default 0,
    Puntualidad_Alumno bool default 0,
    Puntualidad_Profesor bool default 0,
    Manual bool default 0,
    Fecha char(20) not null,
    Hora_Inicio char(8) not null,
    Hora_Fin char(8) not null,
    primary key(ID)
);

alter table `app` add constraint UFK foreign key (ID_Prof) references usuario(ID) ON update CASCADE ON delete CASCADE;

create table if not exists `practicas`(
	ID int auto_increment not null,
    Nombre char(25) not null,
    Practica char(100),
    KeyPath char(100),
    primary key(ID)
);

select * from app;

select * from usuario;

select * from usuario where (ID = "2020630002" OR Correo = "2020630002") AND Password = "1234" LIMIT 1;

delete from usuario where ID = "2020630001";

select * from app;

SELECT *,
	IFNULL((Select Nombre from practicas where ID=app.Practica_Realizar), "") as Practica,
    IFNULL((Select Practica from practicas where ID=app.Practica_Realizar), "") as Ruta,
    IFNULL((Select Nombre from usuario where ID = app.ID_Prof), "") as Profesor
    from app;

select * from usuario;
