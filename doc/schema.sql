create table customer
(
ID int not null auto_increment,
Name varchar(255),
Gender char(1),
IDNum varchar(18),
Phone varchar(11),
primary key (ID)
);

create table reserves
(
ID int not null auto_increment,
Name varchar(255),
Gender char(1),
Phone varchar(11),
RoomID int,
Date date,
primary key (ID)
);

create table orders
(
ID int not null auto_increment,
CustomerID int,
RoomID int,
Date date,
primary key (ID)
);

create table room
(
ID int not null,
primary key (ID)
);
