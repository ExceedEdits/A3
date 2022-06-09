create database valorant_bd;

use valorant_bd;

create table `account` (
`id` int not null auto_increment,
`login` varchar(50) not null,
`password` varchar(20) NOT NULL,
PRIMARY KEY (`id`)
);

select * from account;