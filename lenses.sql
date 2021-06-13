drop table if exists lenses;

create table lenses (
	lens_id int primary key not null auto_increment,
	nickname varchar(50)
);